package com.example.shoppingcart.payment.controller;

import com.example.shoppingcart.payment.model.dto.PaymentDto;
import com.example.shoppingcart.payment.service.api.PaymentBusiness;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentBusiness paymentBusiness;

    @Operation(summary = "${restfulApi.payment.findById.value}", description = "${restfulApi.payment.findById.notes}")
    @GetMapping("/{id}")
    public PaymentDto findById(@PathVariable Long id) {
        return paymentBusiness.findById(id);
    }

    @Operation(summary = "${restfulApi.payment.findAll.value}", description = "${restfulApi.payment.findAll.value}")
    @GetMapping("/find-all")
    public List<PaymentDto> findAll() {
        return paymentBusiness.findAll();
    }

    //    @ApiOperation(value = "${restfulApi.payment.save.value}", notes = "${restfulApi.payment.save.value}")
    @PostMapping("/create")
    public PaymentDto create(@RequestBody PaymentDto paymentDto) {
        return paymentBusiness.save(paymentDto);
    }

    //    @ApiOperation(value = "${restfulApi.payment.update.value}", notes = "${restfulApi.payment.update.value}")
    @PutMapping("/update")
    public void update(@RequestBody PaymentDto paymentDto) {
        paymentBusiness.update(paymentDto);
    }

    //    @ApiOperation(value = "${restfulApi.payment.delete.value}", notes = "${restfulApi.payment.delete.value}")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        paymentBusiness.delete(id);
    }
}
