package service.domain;

public class Order {
    private double totalPrice;

    public Order(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}