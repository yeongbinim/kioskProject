import domain.Menu;
import domain.MenuItem;

import java.util.List;
import java.util.Scanner;

public class Kiosk {
    private final List<Menu> menuList;

    public Kiosk(Menu... menu) {
        menuList = List.of(menu);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(toKioskFormat(getMenus(), "종료하기"));
            int menuNumber = scanner.nextInt(); // 사용자로부터 메뉴 번호 입력받음
            if (menuNumber == 0) { // 0 입력시 종료
                System.out.println("키오스크를 종료합니다.");
                break;
            }
            try {
                String[] menuItems = getMenuItems(menuNumber);
                System.out.println(toKioskFormat(menuItems, "뒤로가기"));
                int menuItemNumber = scanner.nextInt(); // 사용자로부터 메뉴 아이템 번호 입력받음
                if (menuItemNumber == 0) { // 0 입력시 뒤로가기
                    continue;
                }
                String menuItem = getMenuItem(menuNumber, menuItemNumber);
                System.out.println(menuItem);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }
        }
        scanner.close();
    }

    /**
     * 주어진 문자열 배열과 마지막 옵션을 키오스크 포맷에 맞게 포맷팅하여 반환합니다.
     *
     * @param strArr 출력할 항목들의 배열
     * @param zeroPrompt 0번 선택시 표시될 문자열
     * @return 포맷팅된 메뉴 문자열
     */
    private static String toKioskFormat(String[] strArr, String zeroPrompt) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strArr.length; i++) {
            sb.append(String.format("%2d. %s\n", i + 1, strArr[i]));
        }
        sb.append(" 0. ").append(zeroPrompt);
        return sb.toString();
    }

    /**
     * 전체 메뉴 목록을 문자열 배열로 반환합니다.
     *
     * @return 메뉴 목록의 문자열 배열
     */
    private String[] getMenus() {
        String[] menuItems = new String[menuList.size()];
        for (int i = 0; i < menuList.size(); i++) {
            menuItems[i] = menuList.get(i).toString();
        }
        return menuItems;
    }

    /**
     * 주어진 메뉴 번호에 해당하는 메뉴 아이템들을 문자열 배열로 반환합니다.
     *
     * @param menuNumber 메뉴 번호
     * @return 메뉴 아이템의 문자열 배열
     * @throws IllegalArgumentException 유효하지 않은 메뉴 번호 입력 시 발생
     */
    private String[] getMenuItems(int menuNumber) throws IllegalArgumentException {
        if (menuNumber < 1 || menuNumber > menuList.size()) {
            throw new IllegalArgumentException("잘못된 메뉴 번호입니다. 올바른 번호를 입력해주세요.");
        }
        List<MenuItem> menuItemList = menuList.get(menuNumber - 1).getMenuItems();

        String[] menuItems = new String[menuItemList.size()];
        for (int i = 0; i < menuItemList.size(); i++) {
            menuItems[i] = menuItemList.get(i).toString();
        }
        return menuItems;
    }

    /**
     * 주어진 메뉴 번호와 메뉴 아이템 번호에 해당하는 상세 메뉴 아이템 정보를 문자열로 반환합니다.
     *
     * @param menuNumber 메뉴 번호
     * @param menuItemNumber 메뉴 아이템 번호
     * @return 선택된 메뉴 아이템의 상세 정보
     * @throws IllegalArgumentException 유효하지 않은 메뉴 아이템 번호 입력 시 발생
     */
    private String getMenuItem(int menuNumber, int menuItemNumber) throws IllegalArgumentException {
        List<MenuItem> menuItemList = menuList.get(menuNumber - 1).getMenuItems();

        if (menuItemNumber < 1 || menuItemNumber > menuItemList.size()) {
            throw new IllegalArgumentException("잘못된 메뉴 아이템 번호입니다. 올바른 번호를 입력해주세요.");
        }

        MenuItem menuItem = menuItemList.get(menuItemNumber - 1);
        return menuItem.toString();
    }
}