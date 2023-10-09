package com.example.shoppingcart.payment.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class OrderPlacedDeserializer implements Deserializer<OrderPlaced> {
    @Override
    public OrderPlaced deserialize(String topic, byte[] data) {
        try {
            return new ObjectMapper().readValue(data, OrderPlaced.class);
        } catch (IOException e) {
            throw new RuntimeException("Error deserializing OrderPlaced: " + e.getMessage());
        }
    }
}
