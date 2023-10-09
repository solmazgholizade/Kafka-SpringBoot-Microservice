package com.example.shoppingcart.cart.service.mapper;

import com.example.shoppingcart.cart.model.dto.OrderDto;
import com.example.shoppingcart.cart.model.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper MAPPER = Mappers.getMapper(OrderMapper.class);

    OrderEntity toEntity(OrderDto orderDto);

    OrderDto toDto(OrderEntity orderEntity);
}
