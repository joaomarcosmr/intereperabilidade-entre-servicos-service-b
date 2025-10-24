package com.challenge.geosapiens.service_b.infrastructure.usecase.user;

import com.challenge.geosapiens.service_b.application.exception.NotFoundException;
import com.challenge.geosapiens.service_b.domain.entity.User;
import com.challenge.geosapiens.service_b.domain.repository.UserRepository;
import com.challenge.geosapiens.service_b.domain.usecase.user.UpdateUserUseCase;
import com.challenge.geosapiens.service_b.infrastructure.dto.UserDTO;
import com.challenge.geosapiens.service_b.infrastructure.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public User execute(UserDTO userDTO) {
        log.info("[UpdateUserUseCaseImpl] Starting user update with ID: {}", userDTO.getId());

        User existingUser = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> {
                    log.error("[UpdateUserUseCaseImpl] User not found with ID: {}", userDTO.getId());
                    return new NotFoundException("User not found with ID: " + userDTO.getId());
                });

        User user = userMapper.toDomain(userDTO);
        user.setCreatedAt(existingUser.getCreatedAt());
        
        User updatedUser = userRepository.save(user);

        log.info("[UpdateUserUseCaseImpl] User updated successfully with ID: {}", updatedUser.getId());
        return updatedUser;
    }
}
