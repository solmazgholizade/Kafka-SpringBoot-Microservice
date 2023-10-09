package com.example.shoppingcart.cart.model.dto;

import com.example.shoppingcart.cart.model.enums.OrderStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderDto {

    private Long id;

    private Long customerId;

    private OrderStatus status;

    private LocalDate orderDate;

    private Long totalPrice;

//    private List<OrderItemDto> orderItemDtoList;
}
