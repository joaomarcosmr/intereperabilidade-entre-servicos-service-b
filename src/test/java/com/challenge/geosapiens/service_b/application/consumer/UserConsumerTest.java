package com.challenge.geosapiens.service_b.application.consumer;

import com.challenge.geosapiens.service_b.domain.entity.User;
import com.challenge.geosapiens.service_b.domain.usecase.user.CreateUserUseCase;
import com.challenge.geosapiens.service_b.domain.usecase.user.DeleteUserUseCase;
import com.challenge.geosapiens.service_b.domain.usecase.user.UpdateUserUseCase;
import com.challenge.geosapiens.service_b.infrastructure.dto.UserDTO;
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
class UserConsumerTest {

    @Mock
    private CreateUserUseCase createUserUseCase;

    @Mock
    private UpdateUserUseCase updateUserUseCase;

    @Mock
    private DeleteUserUseCase deleteUserUseCase;

    @InjectMocks
    private UserConsumer userConsumer;

    @Test
    void shouldConsumeUserCreateSuccessfully() {
        UUID userId = UUID.randomUUID();
        UserDTO userDTO = new UserDTO(userId, "joao marcos", "joao.marcos@example.com");
        User user = new User();
        user.setId(userId);
        user.setName("joao marcos");
        user.setEmail("joao.marcos@example.com");

        when(createUserUseCase.execute(userDTO)).thenReturn(user);

        userConsumer.consumeUserCreate(userDTO);

        verify(createUserUseCase).execute(userDTO);
    }

    @Test
    void shouldThrowExceptionWhenCreateUserFails() {
        UUID userId = UUID.randomUUID();
        UserDTO userDTO = new UserDTO(userId, "joao marcos", "joao.marcos@example.com");

        when(createUserUseCase.execute(userDTO)).thenThrow(new RuntimeException("Error creating user"));

        assertThrows(RuntimeException.class, () -> userConsumer.consumeUserCreate(userDTO));
        verify(createUserUseCase).execute(userDTO);
    }

    @Test
    void shouldConsumeUserUpdateSuccessfully() {
        UUID userId = UUID.randomUUID();
        UserDTO userDTO = new UserDTO(userId, "joao marcos", "joao.marcos@example.com");
        User user = new User();
        user.setId(userId);
        user.setName("joao marcos");

        when(updateUserUseCase.execute(userDTO)).thenReturn(user);

        userConsumer.consumeUserUpdate(userDTO);

        verify(updateUserUseCase).execute(userDTO);
    }

    @Test
    void shouldConsumeUserDeleteSuccessfully() {
        UUID userId = UUID.randomUUID();
        UserDTO userDTO = new UserDTO(userId, "joao marcos", "joao.marcos@example.com");

        doNothing().when(deleteUserUseCase).execute(userId);

        userConsumer.consumeUserDelete(userDTO);

        verify(deleteUserUseCase).execute(userId);
    }
}

