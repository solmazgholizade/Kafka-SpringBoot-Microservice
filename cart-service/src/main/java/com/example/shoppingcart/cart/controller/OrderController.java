package com.example.shoppingcart.cart.controller;

import com.example.shoppingcart.cart.model.dto.OrderDto;
import com.example.shoppingcart.cart.service.api.OrderBusiness;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderBusiness orderBusiness;

    @Operation(summary = "${restfulApi.order.findById.value}", description = "${restfulApi.order.findById.notes}")
    @GetMapping("/{id}")
    public OrderDto findById(@PathVariable Long id) {
        return orderBusiness.findById(id);
    }

    @Operation(summary = "${restfulApi.order.findAll.value}", description = "${restfulApi.order.findAll.value}")
    @GetMapping("/find-all")
    public List<OrderDto> findAll() {
        return orderBusiness.findAll();
    }

    //    @ApiOperation(value = "${restfulApi.order.save.value}", notes = "${restfulApi.order.save.value}")
    @PostMapping("/create")
    public OrderDto create(@RequestBody OrderDto orderDto) {
        return orderBusiness.save(orderDto);
    }

    //    @ApiOperation(value = "${restfulApi.order.update.value}", notes = "${restfulApi.order.update.value}")
    @PutMapping("/update")
    public void update(@RequestBody OrderDto orderDto) {
        orderBusiness.update(orderDto);
    }

    //    @ApiOperation(value = "${restfulApi.order.delete.value}", notes = "${restfulApi.order.delete.value}")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderBusiness.delete(id);
    }
}
