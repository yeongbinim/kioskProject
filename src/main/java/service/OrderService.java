package service;

import service.domain.Cart;
import service.domain.MenuItem;
import service.domain.Order;

public class OrderService {
    private final Cart cart = new Cart();

    public Order createOrder() {
        return cart.createOrder();
    }

    public void clearCart() {
        cart.clear();
    }

    public void addItemToCart(MenuItem menuItem) {
        cart.addItem(menuItem);
    }

    public String getCartInfo() {
        return cart.toString();
    }

    public boolean cartIsEmpty() {
        return cart.isEmpty();
    }

    public double calculateTotal() {
        return cart.calculateTotalPrice();
    }
}
