package com.example.shoppingcart.cart.controller;

import com.example.shoppingcart.cart.model.dto.OrderItemDto;
import com.example.shoppingcart.cart.model.entity.OrderItemEntity;
import com.example.shoppingcart.cart.service.api.OrderItemBusiness;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-item")
public class OrderItemController {

    @Autowired
    private OrderItemBusiness orderItemBusiness;


    @Operation(summary = "${restfulApi.orderItem.findById.value}", description = "${restfulApi.orderItem.findById.notes}")
    @GetMapping("/{id}")
    public OrderItemEntity findById(@PathVariable Long id) {
        return orderItemBusiness.findById(id);
    }

    @Operation(summary = "${restfulApi.orderItem.findAll.value}", description = "${restfulApi.orderItem.findAll.value}")
    @GetMapping("/find-all")
    public List<OrderItemEntity> findAll() {
        return orderItemBusiness.findAll();
    }

    //    @ApiOperation(value = "${restfulApi.orderItem.save.value}", notes = "${restfulApi.orderItem.save.value}")
    @PostMapping("/create")
    public Long create(@RequestBody OrderItemDto orderItemDto) {
        return orderItemBusiness.save(orderItemDto);
    }

    //    @ApiOperation(value = "${restfulApi.orderItem.update.value}", notes = "${restfulApi.orderItem.update.value}")
    @PutMapping("/update")
    public void update(@RequestBody OrderItemDto orderItemDto) {
        orderItemBusiness.update(orderItemDto);
    }

    //    @ApiOperation(value = "${restfulApi.orderItem.delete.value}", notes = "${restfulApi.orderItem.delete.value}")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderItemBusiness.delete(id);
    }
}
