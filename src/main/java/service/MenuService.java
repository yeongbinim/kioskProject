package service;

import service.domain.Menu;
import service.domain.MenuItem;

import java.util.List;

public class MenuService {
    private final List<Menu> menuList;

    public MenuService(Menu... menu) {
        menuList = List.of(menu);
    }

    /**
     * 등록된 모든 메뉴를 리스트로 반환합니다.
     *
     * @return 메뉴 객체를 포함하는 리스트
     */
    public List<Menu> getMenus() {
        return menuList;
    }

    /**
     * 지정된 메뉴 번호에 해당하는 메뉴의 모든 메뉴 아이템을 리스트로 반환합니다.
     * 메뉴 번호가 유효하지 않을 경우 예외를 발생시킵니다.
     *
     * @param menuNumber 사용자로부터 입력받은 메뉴 번호 (1부터 시작)
     * @return 해당 메뉴에 포함된 모든 메뉴 아이템을 담은 리스트
     * @throws IllegalArgumentException 메뉴 번호가 메뉴 리스트의 범위를 벗어날 때 발생
     */
    public List<MenuItem> getMenuItems(int menuNumber) throws IllegalArgumentException {
        if (menuNumber < 1 || menuNumber > menuList.size()) {
            throw new IllegalArgumentException("잘못된 메뉴 번호입니다. 올바른 번호를 입력해주세요.");
        }
        return menuList.get(menuNumber - 1).getMenuItems();
    }

    /**
     * 지정된 메뉴 번호와 메뉴 아이템 번호를 사용하여 특정 메뉴 아이템의 상세 정보를 반환합니다.
     * 입력된 번호들이 범위를 벗어날 경우 예외를 발생시킵니다.
     *
     * @param menuNumber 사용자가 선택한 메뉴 번호
     * @param menuItemNumber 사용자가 선택한 메뉴 아이템 번호
     * @return 선택된 메뉴 아이템의 상세 정보
     * @throws IllegalArgumentException 메뉴 번호나 메뉴 아이템 번호가 유효하지 않을 때 발생
     */
    public MenuItem getMenuItem(int menuNumber, int menuItemNumber) throws IllegalArgumentException {
        List<MenuItem> menuItemList = menuList.get(menuNumber - 1).getMenuItems();

        if (menuItemNumber < 1 || menuItemNumber > menuItemList.size()) {
            throw new IllegalArgumentException("잘못된 메뉴 아이템 번호입니다. 올바른 번호를 입력해주세요.");
        }

        return menuItemList.get(menuItemNumber - 1);
    }
}
