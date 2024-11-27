package controller;

import service.MenuService;

import java.util.Scanner;

public class Kiosk {
    private final MenuService menuService;

    public Kiosk(MenuService menuService) {
        this.menuService = menuService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(KioskFormatter.formatList(menuService.getMenus(), "종료하기"));
            int menuNumber = scanner.nextInt(); // 사용자로부터 메뉴 번호 입력받음
            if (menuNumber == 0) { // 0 입력시 종료
                System.out.println("키오스크를 종료합니다.");
                break;
            }
            try {
                System.out.println(KioskFormatter.formatList(menuService.getMenuItems(menuNumber), "뒤로가기"));
                int menuItemNumber = scanner.nextInt(); // 사용자로부터 메뉴 아이템 번호 입력받음
                if (menuItemNumber == 0) { // 0 입력시 뒤로가기
                    continue;
                }
                System.out.println(KioskFormatter.formatMessage("선택: " + menuService.getMenuItem(menuNumber, menuItemNumber)));
            } catch (IllegalArgumentException e) {
                System.out.println(KioskFormatter.formatError(e.getMessage()));
                continue;
            }
        }
        scanner.close();
    }
}