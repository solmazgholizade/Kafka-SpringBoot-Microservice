package com.example.shoppingcart.payment.event;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderPlaced {
    @JsonProperty("orderId")
    private Long orderId;

    @JsonProperty("totalPrice")
    private Long totalPrice;

    public OrderPlaced() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }
}
