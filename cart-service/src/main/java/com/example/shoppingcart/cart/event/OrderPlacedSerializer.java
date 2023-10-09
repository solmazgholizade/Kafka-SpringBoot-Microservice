package com.example.shoppingcart.cart.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

import java.io.IOException;

public class OrderPlacedSerializer implements Serializer<OrderPlaced> {
    @Override
    public byte[] serialize(String topic, OrderPlaced orderPlaced) {
        try {
            return new ObjectMapper().writeValueAsBytes(orderPlaced);
        } catch (IOException e) {
            throw new RuntimeException("Error serializing OrderPlaced: " + e.getMessage());
        }
    }
}
