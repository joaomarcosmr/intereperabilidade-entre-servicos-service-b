package com.challenge.geosapiens.service_b.consumer;

import com.challenge.geosapiens.service_b.config.RabbitMQConfig;
import com.challenge.geosapiens.service_b.dto.OrderDTO;
import com.challenge.geosapiens.service_b.entity.Order;
import com.challenge.geosapiens.service_b.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderConsumer {

    private final OrderRepository orderRepository;

    @RabbitListener(queues = RabbitMQConfig.ORDER_CREATE_QUEUE)
    public void consumeOrderCreate(OrderDTO orderDTO) {
        log.info("Received CREATE order from queue: {}", orderDTO);

        try {
            Order order = new Order();
            order.setId(orderDTO.getId());
            order.setDescription(orderDTO.getDescription());
            order.setValue(orderDTO.getValue());
            order.setUserId(orderDTO.getUserId());
            order.setDeliveryPersonId(orderDTO.getDeliveryPersonId());

            Order savedOrder = orderRepository.save(order);
            log.info("Order created successfully with ID: {}", savedOrder.getId());
        } catch (Exception e) {
            log.error("Error creating order: {}", e.getMessage(), e);
            throw e;
        }
    }

    @RabbitListener(queues = RabbitMQConfig.ORDER_UPDATE_QUEUE)
    public void consumeOrderUpdate(OrderDTO orderDTO) {
        log.info("Received UPDATE order from queue: {}", orderDTO);

        try {
            Order order = orderRepository.findById(orderDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderDTO.getId()));

            order.setDescription(orderDTO.getDescription());
            order.setValue(orderDTO.getValue());
            order.setUserId(orderDTO.getUserId());
            order.setDeliveryPersonId(orderDTO.getDeliveryPersonId());

            Order updatedOrder = orderRepository.save(order);
            log.info("Order updated successfully with ID: {}", updatedOrder.getId());
        } catch (Exception e) {
            log.error("Error updating order: {}", e.getMessage(), e);
            throw e;
        }
    }

    @RabbitListener(queues = RabbitMQConfig.ORDER_DELETE_QUEUE)
    public void consumeOrderDelete(OrderDTO orderDTO) {
        log.info("Received DELETE order from queue: {}", orderDTO);

        try {
            orderRepository.deleteById(orderDTO.getId());
            log.info("Order deleted successfully with ID: {}", orderDTO.getId());
        } catch (Exception e) {
            log.error("Error deleting order: {}", e.getMessage(), e);
            throw e;
        }
    }
}
