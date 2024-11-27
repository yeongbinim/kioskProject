package service.domain;

import java.util.List;

public class Menu {
    private final List<MenuItem> menuItems;
    private final String name;

    public Menu(String name, MenuItem... items) {
        this.name = name;
        menuItems = List.of(items);
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public MenuItem getMenuItem(int menuItemNumber) {
        return menuItems.get(menuItemNumber);
    }

    @Override
    public String toString() {
        return name;
    }
}
