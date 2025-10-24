package com.challenge.geosapiens.service_b.infrastructure.usecase.user;

import com.challenge.geosapiens.service_b.application.exception.NotFoundException;
import com.challenge.geosapiens.service_b.domain.entity.User;
import com.challenge.geosapiens.service_b.domain.repository.UserRepository;
import com.challenge.geosapiens.service_b.infrastructure.dto.UserDTO;
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
class UpdateUserUseCaseImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UpdateUserUseCaseImpl updateUserUseCase;

    @Test
    void shouldUpdateUserSuccessfully() {
        UUID userId = UUID.randomUUID();
        UserDTO userDTO = new UserDTO(userId, "joao marcos", "joao.marcos@example.com");
        User existingUser = new User();
        existingUser.setId(userId);
        existingUser.setName("joao");
        existingUser.setEmail("joao@example.com");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(existingUser);

        User result = updateUserUseCase.execute(userDTO);

        assertNotNull(result);
        verify(userRepository).findById(userId);
        verify(userRepository).save(any(User.class));
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        UUID userId = UUID.randomUUID();
        UserDTO userDTO = new UserDTO(userId, "joao marcos", "joao.marcos@example.com");

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> updateUserUseCase.execute(userDTO));
        verify(userRepository).findById(userId);
        verify(userRepository, never()).save(any(User.class));
    }
}

