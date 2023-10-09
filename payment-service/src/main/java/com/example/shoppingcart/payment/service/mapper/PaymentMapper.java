package com.example.shoppingcart.payment.service.mapper;

import com.example.shoppingcart.payment.model.dto.PaymentDto;
import com.example.shoppingcart.payment.model.entity.PaymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaymentMapper {
    PaymentMapper MAPPER = Mappers.getMapper(PaymentMapper.class);

    PaymentEntity toEntity(PaymentDto paymentDto);

    PaymentDto toDto(PaymentEntity paymentEntity);
}
