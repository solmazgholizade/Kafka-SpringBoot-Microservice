package com.example.shoppingcart.payment.service.impl;

import com.example.shoppingcart.payment.exception.EntityNotFoundException;
import com.example.shoppingcart.payment.model.dto.PaymentDto;
import com.example.shoppingcart.payment.model.entity.PaymentEntity;
import com.example.shoppingcart.payment.repository.PaymentRepository;
import com.example.shoppingcart.payment.service.api.PaymentBusiness;
import com.example.shoppingcart.payment.service.mapper.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentBusinessImpl implements PaymentBusiness {

    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public PaymentDto findById(Long id) {
        PaymentEntity paymentEntity = paymentRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("error.payment.not.found.Exception", id));
        return PaymentMapper.MAPPER.toDto(paymentEntity);
    }

    @Override
    public List<PaymentDto> findAll() {
        List<PaymentEntity> paymentEntityList = paymentRepository.findAll();
        return paymentEntityList.stream().map(PaymentMapper.MAPPER::toDto).collect(Collectors.toList());
    }

    @Override
    public PaymentDto save(PaymentDto paymentDto) {
        PaymentEntity paymentEntity = PaymentMapper.MAPPER.toEntity(paymentDto);
        PaymentEntity order = paymentRepository.save(paymentEntity);
        return PaymentMapper.MAPPER.toDto(order);
    }

    @Override
    public void update(PaymentDto paymentDto) {
        PaymentEntity paymentEntity = PaymentMapper.MAPPER.toEntity(paymentDto);
        paymentRepository.save(paymentEntity);
    }

    @Override
    public boolean processPayment(Long orderId, Long totalPrice) {
        System.out.println("orderId: " + orderId);
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setOrderId(orderId);
        paymentDto.setOrderDate(LocalDate.now());
        paymentDto.setTotalPrice(totalPrice);
        save(paymentDto);
        return true;
    }

    @Override
    public void delete(Long id) {
        paymentRepository.deleteById(id);
    }
}
