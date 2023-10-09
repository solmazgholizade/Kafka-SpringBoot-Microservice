package com.example.shoppingcart.payment.event;

import com.example.shoppingcart.payment.service.api.PaymentBusiness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderPlacedConsumer {
    private final Logger logger = LoggerFactory.getLogger(OrderPlacedConsumer.class);

    @Autowired
    PaymentBusiness paymentBusiness;

    @KafkaListener(
            topics = "order-placed",
            groupId = "shopping-cart-group"
    )
    public void handleOrderPlaced(OrderPlaced event) {
        try {
            boolean paymentSuccessful = paymentBusiness.processPayment(event.getOrderId(),event.getTotalPrice());
            if (paymentSuccessful) {
                System.out.println("Payment successful for order ID: " + event.getOrderId());
            } else {
                System.out.println("Payment failed for order ID: " + event.getOrderId());
            }
        } catch (Exception ex) {
            // Handle Kafka exceptions
            if (ex.getCause() instanceof org.apache.kafka.common.errors.SerializationException) {
                logger.error("Serialization error. Check message format and configuration.", ex);
            } else if (ex.getCause() instanceof org.apache.kafka.common.errors.AuthenticationException) {
                logger.error("Authentication error. Check Kafka credentials.", ex);
            } else if (ex.getCause() instanceof org.apache.kafka.common.errors.AuthorizationException) {
                logger.error("Authorization error. Check Kafka access permissions.", ex);
            } else if (ex.getCause() instanceof org.apache.kafka.common.errors.TimeoutException) {
                logger.warn("Kafka timeout error. Retrying...");
            } else if (ex.getCause() instanceof org.apache.kafka.common.errors.OffsetOutOfRangeException) {
                logger.error("Offset out of range error. Handle the offset issue.", ex);
            } else {
                logger.error("Error processing order-placed event:", ex);
                throw ex;
            }
        }
    }
}

