package com.challenge.geosapiens.service_b.application.consumer;

import com.challenge.geosapiens.service_b.application.config.RabbitMQConfig;
import com.challenge.geosapiens.service_b.domain.usecase.order.CreateOrderUseCase;
import com.challenge.geosapiens.service_b.domain.usecase.order.DeleteOrderUseCase;
import com.challenge.geosapiens.service_b.domain.usecase.order.UpdateOrderUseCase;
import com.challenge.geosapiens.service_b.infrastructure.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderConsumer {

    private final CreateOrderUseCase createOrderUseCase;
    private final UpdateOrderUseCase updateOrderUseCase;
    private final DeleteOrderUseCase deleteOrderUseCase;

    @RabbitListener(queues = RabbitMQConfig.ORDER_CREATE_QUEUE)
    public void consumeOrderCreate(OrderDTO orderDTO) {
        log.info("[OrderConsumer] Received CREATE order from queue: {}", orderDTO);

        try {
            createOrderUseCase.execute(orderDTO);
            log.info("[OrderConsumer] CREATE order processed successfully");
        } catch (Exception e) {
            log.error("[OrderConsumer] Error creating order: {}", e.getMessage(), e);
            throw e;
        }
    }

    @RabbitListener(queues = RabbitMQConfig.ORDER_UPDATE_QUEUE)
    public void consumeOrderUpdate(OrderDTO orderDTO) {
        log.info("[OrderConsumer] Received UPDATE order from queue: {}", orderDTO);

        try {
            updateOrderUseCase.execute(orderDTO);
            log.info("[OrderConsumer] UPDATE order processed successfully");
        } catch (Exception e) {
            log.error("[OrderConsumer] Error updating order: {}", e.getMessage(), e);
            throw e;
        }
    }

    @RabbitListener(queues = RabbitMQConfig.ORDER_DELETE_QUEUE)
    public void consumeOrderDelete(OrderDTO orderDTO) {
        log.info("[OrderConsumer] Received DELETE order from queue: {}", orderDTO);

        try {
            deleteOrderUseCase.execute(orderDTO.getId());
            log.info("[OrderConsumer] DELETE order processed successfully");
        } catch (Exception e) {
            log.error("[OrderConsumer] Error deleting order: {}", e.getMessage(), e);
            throw e;
        }
    }
}
