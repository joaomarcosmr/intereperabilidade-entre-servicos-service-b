package com.challenge.geosapiens.service_b.infrastructure.usecase.order;

import com.challenge.geosapiens.service_b.application.exception.NotFoundException;
import com.challenge.geosapiens.service_b.domain.entity.Order;
import com.challenge.geosapiens.service_b.domain.entity.User;
import com.challenge.geosapiens.service_b.domain.repository.OrderRepository;
import com.challenge.geosapiens.service_b.infrastructure.dto.OrderDTO;
import com.challenge.geosapiens.service_b.infrastructure.mapper.OrderMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateOrderUseCaseImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private UpdateOrderUseCaseImpl updateOrderUseCase;

    @Test
    void shouldUpdateOrderSuccessfully() {
        UUID orderId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        OrderDTO orderDTO = new OrderDTO(orderId, "Pizza Calabresa", 50.00, userId, "joao marcos", "11999999999");
        
        User user = new User();
        user.setId(userId);
        user.setName("joao marcos");
        
        Order existingOrder = new Order();
        existingOrder.setId(orderId);
        existingOrder.setDescription("Pizza Margherita");
        existingOrder.setValue(45.90);
        
        Order updatedOrder = new Order();
        updatedOrder.setId(orderId);
        updatedOrder.setDescription("Pizza Calabresa");
        updatedOrder.setValue(50.00);
        updatedOrder.setUser(user);
        updatedOrder.setDeliveryPersonName("joao marcos");

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(existingOrder));
        when(orderMapper.toEntity(orderDTO)).thenReturn(updatedOrder);
        when(orderRepository.save(updatedOrder)).thenReturn(updatedOrder);

        Order result = updateOrderUseCase.execute(orderDTO);

        assertNotNull(result);
        assertEquals("Pizza Calabresa", result.getDescription());
        verify(orderRepository).findById(orderId);
        verify(orderRepository).save(updatedOrder);
    }

    @Test
    void shouldThrowExceptionWhenOrderNotFound() {
        UUID orderId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        OrderDTO orderDTO = new OrderDTO(orderId, "Pizza Calabresa", 50.00, userId, "joao marcos", "11999999999");

        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> updateOrderUseCase.execute(orderDTO));
        verify(orderRepository).findById(orderId);
        verify(orderRepository, never()).save(any(Order.class));
    }
}

