package com.example.shoppingcart.cart.exception;

import lombok.Data;

@Data
public class EntityNotFoundException extends RuntimeException {

    private String message;
    private Object[] arguments;

    public EntityNotFoundException(String message, Object... args) {
        super(message);
        this.message = message;
        this.arguments = args;
    }
}