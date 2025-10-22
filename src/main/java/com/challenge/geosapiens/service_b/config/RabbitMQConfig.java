package com.challenge.geosapiens.service_b.config;

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

    // ========== ORDER EXCHANGE AND QUEUES ==========

    @Bean
    public TopicExchange orderExchange() {
        return new TopicExchange(ORDER_EXCHANGE);
    }

    @Bean
    public DirectExchange orderDlx() {
        return new DirectExchange(ORDER_DLX);
    }

    @Bean
    public Queue orderDlq() {
        return new Queue(ORDER_DLQ, true);
    }

    @Bean
    public Queue orderCreateQueue() {
        return createQueueWithDlx(ORDER_CREATE_QUEUE, ORDER_DLX);
    }

    @Bean
    public Queue orderUpdateQueue() {
        return createQueueWithDlx(ORDER_UPDATE_QUEUE, ORDER_DLX);
    }

    @Bean
    public Queue orderDeleteQueue() {
        return createQueueWithDlx(ORDER_DELETE_QUEUE, ORDER_DLX);
    }

    @Bean
    public Binding orderCreateBinding() {
        return BindingBuilder.bind(orderCreateQueue())
                .to(orderExchange())
                .with(ORDER_CREATE_ROUTING_KEY);
    }

    @Bean
    public Binding orderUpdateBinding() {
        return BindingBuilder.bind(orderUpdateQueue())
                .to(orderExchange())
                .with(ORDER_UPDATE_ROUTING_KEY);
    }

    @Bean
    public Binding orderDeleteBinding() {
        return BindingBuilder.bind(orderDeleteQueue())
                .to(orderExchange())
                .with(ORDER_DELETE_ROUTING_KEY);
    }

    @Bean
    public Binding orderDlqBinding() {
        return BindingBuilder.bind(orderDlq())
                .to(orderDlx())
                .with("#");
    }

    // ========== USER EXCHANGE AND QUEUES ==========

    @Bean
    public TopicExchange userExchange() {
        return new TopicExchange(USER_EXCHANGE);
    }

    @Bean
    public DirectExchange userDlx() {
        return new DirectExchange(USER_DLX);
    }

    @Bean
    public Queue userDlq() {
        return new Queue(USER_DLQ, true);
    }

    @Bean
    public Queue userCreateQueue() {
        return createQueueWithDlx(USER_CREATE_QUEUE, USER_DLX);
    }

    @Bean
    public Queue userUpdateQueue() {
        return createQueueWithDlx(USER_UPDATE_QUEUE, USER_DLX);
    }

    @Bean
    public Queue userDeleteQueue() {
        return createQueueWithDlx(USER_DELETE_QUEUE, USER_DLX);
    }

    @Bean
    public Binding userCreateBinding() {
        return BindingBuilder.bind(userCreateQueue())
                .to(userExchange())
                .with(USER_CREATE_ROUTING_KEY);
    }

    @Bean
    public Binding userUpdateBinding() {
        return BindingBuilder.bind(userUpdateQueue())
                .to(userExchange())
                .with(USER_UPDATE_ROUTING_KEY);
    }

    @Bean
    public Binding userDeleteBinding() {
        return BindingBuilder.bind(userDeleteQueue())
                .to(userExchange())
                .with(USER_DELETE_ROUTING_KEY);
    }

    @Bean
    public Binding userDlqBinding() {
        return BindingBuilder.bind(userDlq())
                .to(userDlx())
                .with("#");
    }

    // ========== DELIVERY PERSON EXCHANGE AND QUEUES ==========

    @Bean
    public TopicExchange deliveryPersonExchange() {
        return new TopicExchange(DELIVERY_PERSON_EXCHANGE);
    }

    @Bean
    public DirectExchange deliveryPersonDlx() {
        return new DirectExchange(DELIVERY_PERSON_DLX);
    }

    @Bean
    public Queue deliveryPersonDlq() {
        return new Queue(DELIVERY_PERSON_DLQ, true);
    }

    @Bean
    public Queue deliveryPersonCreateQueue() {
        return createQueueWithDlx(DELIVERY_PERSON_CREATE_QUEUE, DELIVERY_PERSON_DLX);
    }

    @Bean
    public Queue deliveryPersonUpdateQueue() {
        return createQueueWithDlx(DELIVERY_PERSON_UPDATE_QUEUE, DELIVERY_PERSON_DLX);
    }

    @Bean
    public Queue deliveryPersonDeleteQueue() {
        return createQueueWithDlx(DELIVERY_PERSON_DELETE_QUEUE, DELIVERY_PERSON_DLX);
    }

    @Bean
    public Binding deliveryPersonCreateBinding() {
        return BindingBuilder.bind(deliveryPersonCreateQueue())
                .to(deliveryPersonExchange())
                .with(DELIVERY_PERSON_CREATE_ROUTING_KEY);
    }

    @Bean
    public Binding deliveryPersonUpdateBinding() {
        return BindingBuilder.bind(deliveryPersonUpdateQueue())
                .to(deliveryPersonExchange())
                .with(DELIVERY_PERSON_UPDATE_ROUTING_KEY);
    }

    @Bean
    public Binding deliveryPersonDeleteBinding() {
        return BindingBuilder.bind(deliveryPersonDeleteQueue())
                .to(deliveryPersonExchange())
                .with(DELIVERY_PERSON_DELETE_ROUTING_KEY);
    }

    @Bean
    public Binding deliveryPersonDlqBinding() {
        return BindingBuilder.bind(deliveryPersonDlq())
                .to(deliveryPersonDlx())
                .with("#");
    }

    // Helper method to create queue with DLX configuration
    private Queue createQueueWithDlx(String queueName, String dlxName) {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", dlxName);
        return new Queue(queueName, true, false, false, args);
    }
}
