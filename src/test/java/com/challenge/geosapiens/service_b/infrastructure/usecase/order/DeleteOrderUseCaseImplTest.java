package com.challenge.geosapiens.service_b.infrastructure.usecase.order;

import com.challenge.geosapiens.service_b.application.exception.NotFoundException;
import com.challenge.geosapiens.service_b.domain.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteOrderUseCaseImplTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private DeleteOrderUseCaseImpl deleteOrderUseCase;

    @Test
    void shouldDeleteOrderSuccessfully() {
        UUID orderId = UUID.randomUUID();

        when(orderRepository.existsById(orderId)).thenReturn(true);
        doNothing().when(orderRepository).deleteById(orderId);

        deleteOrderUseCase.execute(orderId);

        verify(orderRepository).existsById(orderId);
        verify(orderRepository).deleteById(orderId);
    }

    @Test
    void shouldThrowExceptionWhenOrderNotFound() {
        UUID orderId = UUID.randomUUID();

        when(orderRepository.existsById(orderId)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> deleteOrderUseCase.execute(orderId));
        verify(orderRepository).existsById(orderId);
        verify(orderRepository, never()).deleteById(any(UUID.class));
    }
}

