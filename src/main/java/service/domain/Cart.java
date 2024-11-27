package service.domain;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private final Map<String, CartItem> itemMap = new HashMap<>();

    public Order createOrder() {
        Order order = new Order(calculateTotalPrice());
        clear();
        return order;
    }

    public void clear() {
        itemMap.clear();
    }

    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (CartItem item : itemMap.values()) {
            totalPrice += item.calculatePrice();
        }
        return totalPrice;
    }

    public void addItem(MenuItem menuItem) {
        CartItem cartItem = itemMap.computeIfAbsent(menuItem.getName(), k -> new CartItem(menuItem, 0));
        cartItem.increaseQuantity();
    }

    public boolean isEmpty() {
        return itemMap.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (CartItem item : itemMap.values()) {
            stringBuilder.append(item.toString()).append("\n");
        }
        return stringBuilder.toString();
    }
}

class CartItem {
    private final MenuItem menuItem;
    private int quantity;

    public CartItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public void increaseQuantity() {
        this.quantity++;
    }

    public double calculatePrice() {
        return menuItem.getValue() * quantity;
    }

    @Override
    public String toString() {
        return String.format("%-20s | W %-4s | %s", menuItem.getName(), menuItem.getValue(), quantity + "개, 총 W " + calculatePrice());
    }
}