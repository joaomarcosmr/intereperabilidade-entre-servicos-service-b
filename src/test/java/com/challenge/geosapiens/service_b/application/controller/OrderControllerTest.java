package com.challenge.geosapiens.service_b.application.controller;

import com.challenge.geosapiens.service_b.domain.entity.Order;
import com.challenge.geosapiens.service_b.domain.entity.User;
import com.challenge.geosapiens.service_b.domain.usecase.order.ListOrdersUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @Mock
    private ListOrdersUseCase listOrdersUseCase;

    @InjectMocks
    private OrderController orderController;

    @Test
    void shouldGetOrdersSuccessfully() {
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

        when(listOrdersUseCase.execute()).thenReturn(orders);

        ResponseEntity<List<Order>> response = orderController.getOrders();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals("Pizza Margherita", response.getBody().get(0).getDescription());
        assertEquals("joao marcos", response.getBody().get(0).getDeliveryPersonName());
        verify(listOrdersUseCase).execute();
    }

    @Test
    void shouldReturnEmptyListWhenNoOrders() {
        when(listOrdersUseCase.execute()).thenReturn(List.of());

        ResponseEntity<List<Order>> response = orderController.getOrders();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());
        verify(listOrdersUseCase).execute();
    }
}

