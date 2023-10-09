package com.example.shoppingcart.cart.event;

import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class
OrderPlacedProducer {
    private final KafkaTemplate<String, OrderPlaced> kafkaTemplate;

    public OrderPlacedProducer(KafkaTemplate<String, OrderPlaced> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderPlacedEvent(Long orderId, Long totalPrice) {
        OrderPlaced orderPlaced = new OrderPlaced(orderId, totalPrice);
        try {
            kafkaTemplate.send("order-placed", orderPlaced);
        } catch (KafkaException e) {
            throw new RuntimeException(e);
        }
    }
}

