package com.challenge.geosapiens.service_b.application.controller;

import com.challenge.geosapiens.service_b.domain.entity.User;
import com.challenge.geosapiens.service_b.domain.usecase.user.ListUsersUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private ListUsersUseCase listUsersUseCase;

    @InjectMocks
    private UserController userController;

    @Test
    void shouldGetUsersSuccessfully() {
        User user1 = new User();
        user1.setId(UUID.randomUUID());
        user1.setName("joao marcos");
        user1.setEmail("joao.marcos@example.com");
        user1.setCreatedAt(LocalDateTime.now());
        user1.setUpdatedAt(LocalDateTime.now());

        User user2 = new User();
        user2.setId(UUID.randomUUID());
        user2.setName("maria silva");
        user2.setEmail("maria.silva@example.com");
        user2.setCreatedAt(LocalDateTime.now());
        user2.setUpdatedAt(LocalDateTime.now());

        List<User> users = Arrays.asList(user1, user2);

        when(listUsersUseCase.execute()).thenReturn(users);

        ResponseEntity<List<User>> response = userController.getUsers();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals("joao marcos", response.getBody().get(0).getName());
        assertEquals("joao.marcos@example.com", response.getBody().get(0).getEmail());
        verify(listUsersUseCase).execute();
    }

    @Test
    void shouldReturnEmptyListWhenNoUsers() {
        when(listUsersUseCase.execute()).thenReturn(List.of());

        ResponseEntity<List<User>> response = userController.getUsers();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());
        verify(listUsersUseCase).execute();
    }
}

