package com.example.shoppingcart.cart.service.impl;

import com.example.shoppingcart.cart.event.OrderPlacedProducer;
import com.example.shoppingcart.cart.exception.EntityNotFoundException;
import com.example.shoppingcart.cart.model.dto.OrderDto;
import com.example.shoppingcart.cart.model.entity.OrderEntity;
import com.example.shoppingcart.cart.repository.OrderRepository;
import com.example.shoppingcart.cart.service.api.OrderBusiness;
import com.example.shoppingcart.cart.service.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderBusinessImpl implements OrderBusiness {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderPlacedProducer orderPlacedProducer;

    @Override
    public OrderDto findById(Long id) {
        OrderEntity orderEntity = orderRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("error.order.not.found.Exception", id));
        return OrderMapper.MAPPER.toDto(orderEntity);
    }

    @Override
    public List<OrderDto> findAll() {
        List<OrderEntity> orderEntityList = orderRepository.findAll();
        return orderEntityList.stream().map(OrderMapper.MAPPER::toDto).collect(Collectors.toList());
    }

    @Override
    public OrderDto save(OrderDto orderDto) {
        OrderEntity orderEntity = OrderMapper.MAPPER.toEntity(orderDto);
        OrderEntity order = orderRepository.save(orderEntity);
        orderPlacedProducer.sendOrderPlacedEvent(order.getId(),order.getTotalPrice());
        return OrderMapper.MAPPER.toDto(order);
    }

    @Override
    public void update(OrderDto orderDto) {
        OrderEntity orderEntity = OrderMapper.MAPPER.toEntity(orderDto);
        orderRepository.save(orderEntity);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
