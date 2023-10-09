package com.example.shoppingcart.cart.event;

public class OrderPlaced {
    private final Long orderId;
    private final Long totalPrice;

    public OrderPlaced(Long orderId, Long totalPrice) {
        this.orderId = orderId;
        this.totalPrice = totalPrice;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }
}
