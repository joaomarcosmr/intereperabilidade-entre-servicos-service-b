package com.challenge.geosapiens.service_b.infrastructure.usecase.user;

import com.challenge.geosapiens.service_b.domain.entity.User;
import com.challenge.geosapiens.service_b.domain.repository.UserRepository;
import com.challenge.geosapiens.service_b.domain.usecase.user.UpdateUserUseCase;
import com.challenge.geosapiens.service_b.infrastructure.dto.UserDTO;
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

    @Override
    @Transactional
    public User execute(UserDTO userDTO) {
        log.debug("Updating user with ID: {}", userDTO.getId());

        User user = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userDTO.getId()));

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setUpdatedAt(LocalDateTime.now());

        User updatedUser = userRepository.save(user);

        log.info("User updated successfully with ID: {}", updatedUser.getId());
        return updatedUser;
    }
}
