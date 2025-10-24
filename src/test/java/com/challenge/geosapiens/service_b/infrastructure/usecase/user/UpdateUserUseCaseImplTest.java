package com.challenge.geosapiens.service_b.infrastructure.usecase.user;

import com.challenge.geosapiens.service_b.application.exception.NotFoundException;
import com.challenge.geosapiens.service_b.domain.entity.User;
import com.challenge.geosapiens.service_b.domain.repository.UserRepository;
import com.challenge.geosapiens.service_b.infrastructure.dto.UserDTO;
import com.challenge.geosapiens.service_b.infrastructure.mapper.UserMapper;
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

    @Mock
    private UserMapper userMapper;

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

        User updatedUser = new User();
        updatedUser.setId(userId);
        updatedUser.setName("joao marcos");
        updatedUser.setEmail("joao.marcos@example.com");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userMapper.toDomain(userDTO)).thenReturn(updatedUser);
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        User result = updateUserUseCase.execute(userDTO);

        assertNotNull(result);
        assertEquals("joao marcos", result.getName());
        assertEquals("joao.marcos@example.com", result.getEmail());
        verify(userRepository).findById(userId);
        verify(userMapper).toDomain(userDTO);
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

