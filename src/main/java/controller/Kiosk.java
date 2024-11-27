package controller;

import service.MenuService;
import service.OrderService;
import service.domain.Menu;
import service.domain.MenuItem;
import service.domain.Order;

import java.util.List;
import java.util.Scanner;

public class Kiosk {
    private final MenuService menuService;
    private final OrderService orderService;
    private final Scanner scanner = new Scanner(System.in);

    public Kiosk(MenuService menuService, OrderService orderService) {
        this.menuService = menuService;
        this.orderService = orderService;
    }

    public void start() {
        while (true) {
            displayMenu();
            int menuNumber = scanner.nextInt();

            if (menuNumber == 0) {
                System.out.println(KioskFormatter.formatMessage("키오스크를 종료합니다."));
                break;
            }

            if (!selectMenu(menuNumber)) {
                break;
            }
        }
        scanner.close();
    }

    private void displayMenu() {
        List<Menu> menuList = menuService.getMenus();
        System.out.println(KioskFormatter.formatMessage("[ MAIN MENU ]"));
        System.out.println(KioskFormatter.formatList(menuList, "종료하기"));

        if (!orderService.cartIsEmpty()) {
            System.out.println(KioskFormatter.formatMessage("[ ORDER MENU ]"));
            System.out.println(KioskFormatter.formatList(menuList.size() + 1, "Orders", "Cancel"));
        }
    }

    // 1. 메뉴 고르기
    private boolean selectMenu(int menuNumber) {
        List<Menu> menuList = menuService.getMenus();
        if (menuNumber == menuList.size() + 1) {
            return processOrder();
        } else if (menuNumber == menuList.size() + 2) {
            orderService.clearCart();
            return true;
        } else {
            selectMenuItem(menuNumber);
            return true;
        }
    }

    // 2. 메뉴 아이템 고르기
    private void selectMenuItem(int menuNumber) {
        try {
            List<MenuItem> menuItemList = menuService.getMenuItems(menuNumber);
            System.out.println(KioskFormatter.formatList(menuItemList, "뒤로가기"));
            int menuItemNumber = scanner.nextInt();
            if (menuItemNumber != 0) {
                MenuItem menuItem = menuService.getMenuItem(menuNumber, menuItemNumber);
                addItemToCart(menuItem);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(KioskFormatter.formatError(e.getMessage()));
        }
    }

    // 3. 메뉴 아이템 주문하기
    private void addItemToCart(MenuItem menuItem) {
        System.out.println("선택: " + menuItem);
        System.out.println(KioskFormatter.formatMessage("위 메뉴를 장바구니에 추가하시겠습니까? \n 1. 예     2. 아니오"));
        if (scanner.nextInt() == 1) {
            orderService.addItemToCart(menuItem);
        }
    }

    // F. 주문하기
    private boolean processOrder() {
        System.out.println(KioskFormatter.formatMessage("아래와 같이 주문 하시겠습니까? \n [ Orders ]"));
        System.out.println(orderService.getCartInfo());
        System.out.println(KioskFormatter.formatMessage("[ Total ]"));
        System.out.println("W " + orderService.calculateTotal());
        System.out.println(KioskFormatter.formatList(1, "주문", "메뉴판"));

        if (scanner.nextInt() == 1) {
            Order order = orderService.createOrder();
            System.out.println(KioskFormatter.formatMessage("주문이 완료되었습니다. 금액은 W " + order.getTotalPrice() + " 입니다."));
            return false; // 종료
        }
        return true; // 계속
    }
}
