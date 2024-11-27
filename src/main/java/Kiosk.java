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
            printMenuItems();
            if (!selectMenuItem(scanner)) {
                break;
            }
        }
        scanner.close();
    }

    /**
     * 메뉴 아이템들을 화면에 출력합니다.
     * 각 메뉴 아이템 옆에는 사용자가 선택할 수 있는 번호가 표시됩니다.
     * 목록의 마지막에는 사용자가 주문을 종료할 수 있는 옵션도 함께 출력됩니다.
     */
    private void printMenuItems() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < menuItemList.size(); i++) {
            sb.append(String.format("%2d. %s\n", i + 1, menuItemList.get(i)));
        }
        sb.append(String.format("%2d. 종료\n\n", 0)); // 종료 옵션 추가
        sb.append("메뉴를 입력하세요: "); // 사용자 입력 안내 메시지

        System.out.println(sb);
    }

    /**
     * 사용자로부터 메뉴 선택을 입력 받아 해당 메뉴의 상세 정보를 출력합니다.
     *
     * @param scanner 사용자 입력을 받기 위한 Scanner 객체
     * @return 사용자가 주문 종료를 원할 때 false, 계속 주문을 원할 때 true 반환
     */
    private boolean selectMenuItem(Scanner scanner) {
        while (true) {
            int menuNumber = scanner.nextInt();
            if (menuNumber == 0) {
                return false;
            } else if (menuNumber > 0 && menuNumber <= menuItemList.size()) {
                MenuItem menuItem = menuItemList.get(menuNumber - 1);
                System.out.println(menuItem);
                return true;
            } else {
                System.out.println("잘못된 메뉴 번호입니다. 다시 선택해주세요.\n");
            }
        }
    }
}
