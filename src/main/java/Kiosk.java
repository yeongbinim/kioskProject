import domain.MenuItem;

import java.util.List;
import java.util.Scanner;

public class Kiosk {
    private final List<MenuItem> menuItemList;

    public Kiosk(MenuItem... menu) {
        menuItemList = List.of(menu);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(getMenuItems());
            System.out.print(" 0. 종료\n\n메뉴를 입력하세요: ");
            int menuItemNumber = scanner.nextInt(); // 사용자로부터 메뉴 번호 입력받음
            if (menuItemNumber == 0) { // 0 입력시 종료
                System.out.println("키오스크를 종료합니다.");
                break;
            }
            try {
                System.out.println(getMenuItem(menuItemNumber));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage()); // 잘못된 입력 처리 메시지 출력
                continue;
            }
        }
        scanner.close();
    }

    /**
     * 전체 메뉴 아이템 목록을 문자열 형태로 반환합니다.
     * 각 메뉴 아이템 옆에는 사용자가 선택할 수 있는 번호가 표시됩니다.
     *
     * @return 메뉴 아이템들의 목록을 문자열로 표시
     */
    private String getMenuItems() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < menuItemList.size(); i++) {
            sb.append(String.format("%2d. %s\n", i + 1, menuItemList.get(i)));
        }
        return sb.toString();
    }

    /**
     * 사용자가 선택한 메뉴 번호에 해당하는 메뉴 아이템의 상세 정보를 반환합니다.
     * 입력된 메뉴 번호가 범위를 벗어날 경우 예외를 발생시킵니다.
     *
     * @param menuItemNumber 사용자가 입력한 메뉴 번호
     * @return 선택된 메뉴 아이템의 정보 문자열
     * @throws IllegalArgumentException 선택된 메뉴 번호가 유효 범위를 벗어날 경우 발생
     */
    private String getMenuItem(int menuItemNumber) {
        if (menuItemNumber < 1 || menuItemNumber > menuItemList.size()) {
            throw new IllegalArgumentException("잘못된 메뉴 번호입니다. 올바른 번호를 입력해주세요.");
        }
        MenuItem menuItem = menuItemList.get(menuItemNumber - 1);
        return menuItem.toString();
    }
}