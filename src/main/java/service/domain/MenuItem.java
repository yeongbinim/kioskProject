package service.domain;

public class MenuItem {
    private final String name;
    private final Double value;
    private final String description;

    public MenuItem(String name, Double value, String description) {
        this.name = name;
        this.value = value;
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("%-20s | W %-4s | %s", name, value, description);
    }
}
