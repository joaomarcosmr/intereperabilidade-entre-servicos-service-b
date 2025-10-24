package com.challenge.geosapiens.service_b.infrastructure.usecase.order;

import com.challenge.geosapiens.service_b.application.exception.AlreadyExistsException;
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

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateOrderUseCaseImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private CreateOrderUseCaseImpl createOrderUseCase;

    @Test
    void shouldCreateOrderSuccessfully() {
        UUID orderId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        OrderDTO orderDTO = new OrderDTO(orderId, "Pizza Margherita", 45.90, userId, "joao marcos", "11999999999");
        
        User user = new User();
        user.setId(userId);
        user.setName("joao marcos");
        
        Order order = new Order();
        order.setId(orderId);
        order.setDescription("Pizza Margherita");
        order.setValue(45.90);
        order.setUser(user);
        order.setDeliveryPersonName("joao marcos");
        order.setDeliveryPersonPhone("11999999999");

        when(orderRepository.existsById(orderId)).thenReturn(false);
        when(orderMapper.toEntity(orderDTO)).thenReturn(order);
        when(orderRepository.save(order)).thenReturn(order);

        Order result = createOrderUseCase.execute(orderDTO);

        assertNotNull(result);
        assertEquals("Pizza Margherita", result.getDescription());
        assertEquals("joao marcos", result.getDeliveryPersonName());
        verify(orderRepository).existsById(orderId);
        verify(orderRepository).save(order);
    }

    @Test
    void shouldThrowExceptionWhenOrderAlreadyExists() {
        UUID orderId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        OrderDTO orderDTO = new OrderDTO(orderId, "Pizza Margherita", 45.90, userId, "joao marcos", "11999999999");

        when(orderRepository.existsById(orderId)).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> createOrderUseCase.execute(orderDTO));
        verify(orderRepository).existsById(orderId);
        verify(orderRepository, never()).save(any(Order.class));
    }
}

