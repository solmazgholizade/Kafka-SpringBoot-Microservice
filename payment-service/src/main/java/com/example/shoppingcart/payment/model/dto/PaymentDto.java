package com.example.shoppingcart.payment.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PaymentDto {

    private Long id;

    private Long orderId;

    private LocalDate orderDate;

    private Long totalPrice;
}
