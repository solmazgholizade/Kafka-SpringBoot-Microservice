package com.example.shoppingcart.cart.service.api;

import com.example.shoppingcart.cart.model.dto.OrderDto;

import java.util.List;

public interface OrderBusiness {
    OrderDto findById(Long id);

    List<OrderDto> findAll();

    OrderDto save(OrderDto orderDto);

    void update(OrderDto orderDto);

    void delete(Long id);
}
