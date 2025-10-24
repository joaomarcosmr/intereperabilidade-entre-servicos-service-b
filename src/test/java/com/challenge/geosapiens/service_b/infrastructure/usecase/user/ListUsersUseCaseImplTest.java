package com.challenge.geosapiens.service_b.infrastructure.usecase.user;

import com.challenge.geosapiens.service_b.domain.entity.User;
import com.challenge.geosapiens.service_b.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListUsersUseCaseImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ListUsersUseCaseImpl listUsersUseCase;

    @Test
    void shouldListAllUsersSuccessfully() {
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

        when(userRepository.findAll()).thenReturn(users);

        List<User> result = listUsersUseCase.execute();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("joao marcos", result.get(0).getName());
        assertEquals("maria silva", result.get(1).getName());
        verify(userRepository).findAll();
    }

    @Test
    void shouldReturnEmptyListWhenNoUsers() {
        when(userRepository.findAll()).thenReturn(List.of());

        List<User> result = listUsersUseCase.execute();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(userRepository).findAll();
    }
}

