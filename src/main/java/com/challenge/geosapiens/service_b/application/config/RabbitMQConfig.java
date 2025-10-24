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

    public static final String ORDER_CREATE_QUEUE = "order.create.queue";
    public static final String ORDER_UPDATE_QUEUE = "order.update.queue";
    public static final String ORDER_DELETE_QUEUE = "order.delete.queue";

    public static final String USER_CREATE_QUEUE = "user.create.queue";
    public static final String USER_UPDATE_QUEUE = "user.update.queue";
    public static final String USER_DELETE_QUEUE = "user.delete.queue";

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
