package com.example.shoppingcart.cart.service.impl;


import com.example.shoppingcart.cart.exception.EntityNotFoundException;
import com.example.shoppingcart.cart.model.dto.OrderItemDto;
import com.example.shoppingcart.cart.model.entity.OrderItemEntity;
import com.example.shoppingcart.cart.repository.OrderItemRepository;
import com.example.shoppingcart.cart.service.api.OrderItemBusiness;
import com.example.shoppingcart.cart.service.mapper.OrderItemMapper;import com.example.shoppingcart.cart.service.mapper.OrderItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderItemBusinessImpl implements OrderItemBusiness {

    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public OrderItemEntity findById(Long id) {
        return orderItemRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("error.order.not.found.Exception", id));
    }

    @Override
    public List<OrderItemEntity> findAll() {
        return orderItemRepository.findAll();
    }

    @Transactional
    @Override
    public Long save(OrderItemDto orderItemDto) {
        OrderItemEntity orderItemEntity = OrderItemMapper.MAPPER.toEntity(orderItemDto);
        return orderItemRepository.save(orderItemEntity).getId();
    }

    @Transactional
    @Override
    public void update(OrderItemDto orderItemDto) {
        OrderItemEntity orderItemEntity = OrderItemMapper.MAPPER.toEntity(orderItemDto);
        orderItemRepository.save(orderItemEntity);
    }

    @Override
    public void delete(Long id) {
        orderItemRepository.deleteById(id);
    }
}
