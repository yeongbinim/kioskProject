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

    public Kiosk(MenuService menuService, OrderService orderService) {
        this.menuService = menuService;
        this.orderService = orderService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            List<Menu> menuList = menuService.getMenus();
            System.out.println(KioskFormatter.formatMessage("[ MAIN MENU ]"));
            System.out.println(KioskFormatter.formatList(menuService.getMenus(), "종료하기"));
            if (!orderService.cartIsEmpty()) { //카트에 담은게 있다면
                System.out.println(KioskFormatter.formatMessage("[ ORDER MENU ]"));
                System.out.println(KioskFormatter.formatList(menuList.size() + 1, "Orders", "Cancel"));
            }

            int menuNumber = scanner.nextInt(); // 사용자로부터 메뉴 번호 입력받음
            if (menuNumber == 0) { // 0 입력시 종료
                System.out.println(KioskFormatter.formatMessage("키오스크를 종료합니다."));
                break;
            } else if (menuNumber == menuList.size() + 1) { // 주문선택
                System.out.println(KioskFormatter.formatMessage("아래와 같이 주문 하시겠습니까? \n [ Orders ]"));
                System.out.println(orderService.getCartInfo());
                System.out.println(KioskFormatter.formatMessage("[ Total ]"));
                System.out.println("W " + orderService.calculateTotal());
                System.out.println(KioskFormatter.formatList(1, "주문", "메뉴판"));
                int selected = scanner.nextInt();
                if (selected == 1) {
                    Order order = orderService.createOrder();
                    System.out.println(KioskFormatter.formatMessage("주문이 완료되었습니다. 금액은 W " + order.getTotalPrice() + " 입니다."));
                }
                continue;
            } else if (menuNumber == menuList.size() + 2) { // 취소선택
                orderService.clearCart();
                continue;
            }

            try {
                List<MenuItem> menuItemList = menuService.getMenuItems(menuNumber);
                System.out.println(KioskFormatter.formatList(menuItemList, "뒤로가기"));
                int menuItemNumber = scanner.nextInt(); // 사용자로부터 메뉴 아이템 번호 입력받음
                if (menuItemNumber == 0) { // 0 입력시 뒤로가기
                    continue;
                }
                MenuItem menuItem = menuService.getMenuItem(menuNumber, menuItemNumber);
                System.out.println("선택: " + menuItem);
                System.out.println(KioskFormatter.formatMessage("위 메뉴를 장바구니에 추가하시겠습니까? \n 1. 예     2. 아니오"));
                if (scanner.nextInt() == 1) {
                    orderService.addItemToCart(menuItem);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(KioskFormatter.formatError(e.getMessage()));
            }
        }
        scanner.close();
    }
}