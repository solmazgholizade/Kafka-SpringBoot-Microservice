package com.example.shoppingcart.payment;

import com.example.shoppingcart.payment.event.OrderPlaced;
import com.example.shoppingcart.payment.event.OrderPlacedDeserializer;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.BackOff;
import org.springframework.util.backoff.FixedBackOff;

import java.util.HashMap;
import java.util.Map;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Payment-Service API", description = "Shopping Cart"))
public class PaymentServiceConfiguration {

    @Bean
    public ConsumerFactory<String, OrderPlaced> consumerFactory() {
        Map<String, Object> consumerProps = new HashMap<>();
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, "shopping-cart-group");
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, OrderPlacedDeserializer.class.getName()); // Use your custom deserializer

        return new DefaultKafkaConsumerFactory<>(consumerProps);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, OrderPlaced> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, OrderPlaced> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setCommonErrorHandler(errorHandler());
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.RECORD);
        factory.afterPropertiesSet();
        return factory;
    }


    @Bean
    public DefaultErrorHandler errorHandler() {
        BackOff fixedBackOff = new FixedBackOff(1000, 3);
        DefaultErrorHandler errorHandler = new DefaultErrorHandler((consumerRecord, exception) -> {
            // The logic that executes when all retries have finished
            System.out.println("All retry attempts exhausted for message:");
            System.out.println("Topic: " + consumerRecord.topic());
            System.out.println("Partition: " + consumerRecord.partition());
            System.out.println("Offset: " + consumerRecord.offset());
            System.out.println("Key: " + consumerRecord.key());
            System.out.println("Value: " + consumerRecord.value());
            System.out.println("Exception: " + exception.getMessage());
        }, fixedBackOff);
        return errorHandler;
    }
}
