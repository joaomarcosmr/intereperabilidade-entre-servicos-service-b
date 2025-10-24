package com.challenge.geosapiens.service_b.infrastructure.usecase.user;

import com.challenge.geosapiens.service_b.application.exception.AlreadyExistsException;
import com.challenge.geosapiens.service_b.domain.entity.User;
import com.challenge.geosapiens.service_b.domain.repository.UserRepository;
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
class CreateUserUseCaseImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CreateUserUseCaseImpl createUserUseCase;

    @Test
    void shouldCreateUserSuccessfully() {
        UUID userId = UUID.randomUUID();
        UserDTO userDTO = new UserDTO(userId, "joao marcos", "joao.marcos@example.com");
        User user = new User();
        user.setId(userId);
        user.setName("joao marcos");
        user.setEmail("joao.marcos@example.com");

        when(userRepository.existsById(userId)).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = createUserUseCase.execute(userDTO);

        assertNotNull(result);
        assertEquals("joao marcos", result.getName());
        assertEquals("joao.marcos@example.com", result.getEmail());
        verify(userRepository).existsById(userId);
        verify(userRepository).save(any(User.class));
    }

    @Test
    void shouldThrowExceptionWhenUserAlreadyExists() {
        UUID userId = UUID.randomUUID();
        UserDTO userDTO = new UserDTO(userId, "joao marcos", "joao.marcos@example.com");

        when(userRepository.existsById(userId)).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> createUserUseCase.execute(userDTO));
        verify(userRepository).existsById(userId);
        verify(userRepository, never()).save(any(User.class));
    }
}

