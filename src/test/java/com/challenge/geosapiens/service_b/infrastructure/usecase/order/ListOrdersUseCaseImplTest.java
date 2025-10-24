package com.challenge.geosapiens.service_b.infrastructure.usecase.order;

import com.challenge.geosapiens.service_b.domain.entity.Order;
import com.challenge.geosapiens.service_b.domain.entity.User;
import com.challenge.geosapiens.service_b.domain.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListOrdersUseCaseImplTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private ListOrdersUseCaseImpl listOrdersUseCase;

    @Test
    void shouldListAllOrdersSuccessfully() {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setName("joao marcos");
        user.setEmail("joao.marcos@example.com");

        Order order1 = new Order();
        order1.setId(UUID.randomUUID());
        order1.setDescription("Pizza Margherita");
        order1.setValue(45.90);
        order1.setUser(user);
        order1.setDeliveryPersonName("joao marcos");

        Order order2 = new Order();
        order2.setId(UUID.randomUUID());
        order2.setDescription("Pizza Calabresa");
        order2.setValue(50.00);
        order2.setUser(user);
        order2.setDeliveryPersonName("joao marcos");

        List<Order> orders = Arrays.asList(order1, order2);

        when(orderRepository.findAllOrders()).thenReturn(orders);

        List<Order> result = listOrdersUseCase.execute();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Pizza Margherita", result.get(0).getDescription());
        assertEquals("Pizza Calabresa", result.get(1).getDescription());
        verify(orderRepository).findAllOrders();
    }

    @Test
    void shouldReturnEmptyListWhenNoOrders() {
        when(orderRepository.findAllOrders()).thenReturn(List.of());

        List<Order> result = listOrdersUseCase.execute();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(orderRepository).findAllOrders();
    }
}

