package com.challenge.geosapiens.service_b.application.consumer;

import com.challenge.geosapiens.service_b.domain.entity.Order;
import com.challenge.geosapiens.service_b.domain.usecase.order.CreateOrderUseCase;
import com.challenge.geosapiens.service_b.domain.usecase.order.DeleteOrderUseCase;
import com.challenge.geosapiens.service_b.domain.usecase.order.UpdateOrderUseCase;
import com.challenge.geosapiens.service_b.infrastructure.dto.OrderDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderConsumerTest {

    @Mock
    private CreateOrderUseCase createOrderUseCase;

    @Mock
    private UpdateOrderUseCase updateOrderUseCase;

    @Mock
    private DeleteOrderUseCase deleteOrderUseCase;

    @InjectMocks
    private OrderConsumer orderConsumer;

    @Test
    void shouldConsumeOrderCreateSuccessfully() {
        UUID orderId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        OrderDTO orderDTO = new OrderDTO(orderId, "Pizza Margherita", 45.90, userId, "joao marcos", "11999999999");
        Order order = new Order();
        order.setId(orderId);
        order.setDescription("Pizza Margherita");
        order.setDeliveryPersonName("joao marcos");

        when(createOrderUseCase.execute(orderDTO)).thenReturn(order);

        orderConsumer.consumeOrderCreate(orderDTO);

        verify(createOrderUseCase).execute(orderDTO);
    }

    @Test
    void shouldThrowExceptionWhenCreateOrderFails() {
        UUID orderId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        OrderDTO orderDTO = new OrderDTO(orderId, "Pizza Margherita", 45.90, userId, "joao marcos", "11999999999");

        when(createOrderUseCase.execute(orderDTO)).thenThrow(new RuntimeException("Error creating order"));

        assertThrows(RuntimeException.class, () -> orderConsumer.consumeOrderCreate(orderDTO));
        verify(createOrderUseCase).execute(orderDTO);
    }

    @Test
    void shouldConsumeOrderUpdateSuccessfully() {
        UUID orderId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        OrderDTO orderDTO = new OrderDTO(orderId, "Pizza Calabresa", 50.00, userId, "joao marcos", "11999999999");
        Order order = new Order();
        order.setId(orderId);
        order.setDescription("Pizza Calabresa");

        when(updateOrderUseCase.execute(orderDTO)).thenReturn(order);

        orderConsumer.consumeOrderUpdate(orderDTO);

        verify(updateOrderUseCase).execute(orderDTO);
    }

    @Test
    void shouldConsumeOrderDeleteSuccessfully() {
        UUID orderId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        OrderDTO orderDTO = new OrderDTO(orderId, "Pizza Margherita", 45.90, userId, "joao marcos", "11999999999");

        doNothing().when(deleteOrderUseCase).execute(orderId);

        orderConsumer.consumeOrderDelete(orderDTO);

        verify(deleteOrderUseCase).execute(orderId);
    }
}

