package com.example.shoppingcart.payment.service.api;

import com.example.shoppingcart.payment.model.dto.PaymentDto;

import java.util.List;

public interface PaymentBusiness {
    PaymentDto findById(Long id);

    List<PaymentDto> findAll();

    PaymentDto save(PaymentDto paymentDto);

    void update(PaymentDto paymentDto);

    boolean processPayment(Long orderId, Long totalPrice);

    void delete(Long id);
}
