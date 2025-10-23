package com.challenge.geosapiens.service_b.application.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {

    // Exchange names
    public static final String ORDER_EXCHANGE = "order.exchange";
    public static final String USER_EXCHANGE = "user.exchange";
    public static final String DELIVERY_PERSON_EXCHANGE = "delivery-person.exchange";

    // Order queues
    public static final String ORDER_CREATE_QUEUE = "order.create.queue";
    public static final String ORDER_UPDATE_QUEUE = "order.update.queue";
    public static final String ORDER_DELETE_QUEUE = "order.delete.queue";

    // User queues
    public static final String USER_CREATE_QUEUE = "user.create.queue";
    public static final String USER_UPDATE_QUEUE = "user.update.queue";
    public static final String USER_DELETE_QUEUE = "user.delete.queue";

    // DeliveryPerson queues
    public static final String DELIVERY_PERSON_CREATE_QUEUE = "delivery-person.create.queue";
    public static final String DELIVERY_PERSON_UPDATE_QUEUE = "delivery-person.update.queue";
    public static final String DELIVERY_PERSON_DELETE_QUEUE = "delivery-person.delete.queue";

    // Routing keys
    public static final String ORDER_CREATE_ROUTING_KEY = "order.create";
    public static final String ORDER_UPDATE_ROUTING_KEY = "order.update";
    public static final String ORDER_DELETE_ROUTING_KEY = "order.delete";

    public static final String USER_CREATE_ROUTING_KEY = "user.create";
    public static final String USER_UPDATE_ROUTING_KEY = "user.update";
    public static final String USER_DELETE_ROUTING_KEY = "user.delete";

    public static final String DELIVERY_PERSON_CREATE_ROUTING_KEY = "delivery-person.create";
    public static final String DELIVERY_PERSON_UPDATE_ROUTING_KEY = "delivery-person.update";
    public static final String DELIVERY_PERSON_DELETE_ROUTING_KEY = "delivery-person.delete";

    // DLQ names
    public static final String ORDER_DLQ = "order.dlq";
    public static final String USER_DLQ = "user.dlq";
    public static final String DELIVERY_PERSON_DLQ = "delivery-person.dlq";

    // DLX names
    public static final String ORDER_DLX = "order.dlx";
    public static final String USER_DLX = "user.dlx";
    public static final String DELIVERY_PERSON_DLX = "delivery-person.dlx";

    // Message Converter
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
