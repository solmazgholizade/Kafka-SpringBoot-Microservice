package com.example.shoppingcart.cart.service.mapper;


import com.example.shoppingcart.cart.model.dto.OrderItemDto;
import com.example.shoppingcart.cart.model.entity.OrderItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderItemMapper {
    OrderItemMapper MAPPER = Mappers.getMapper(OrderItemMapper.class);

    OrderItemEntity toEntity(OrderItemDto orderItemDto);

    OrderItemDto toDto(OrderItemEntity orderItemEntity);
}
