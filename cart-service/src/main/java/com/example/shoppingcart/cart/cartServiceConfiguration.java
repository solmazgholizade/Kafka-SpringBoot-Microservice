package com.example.shoppingcart.cart;

import com.example.shoppingcart.cart.event.OrderPlaced;
import com.example.shoppingcart.cart.event.OrderPlacedSerializer;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;
import java.util.Map;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Cart-Service API", description = "Shopping Cart"))
public class cartServiceConfiguration {

    @Bean
    public KafkaTemplate<String, OrderPlaced> kafkaTemplate() {
        Map<String, Object> producerProps = new HashMap<>();
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, OrderPlacedSerializer.class);
        // Configure retries and error handling
        producerProps.put(ProducerConfig.RETRIES_CONFIG, 3);
        DefaultKafkaProducerFactory<String, OrderPlaced> producerFactory = new DefaultKafkaProducerFactory<>(producerProps);
        return new KafkaTemplate<>(producerFactory);
    }
}
