package com.challenge.geosapiens.service_b.infrastructure.usecase.user;

import com.challenge.geosapiens.service_b.domain.entity.User;
import com.challenge.geosapiens.service_b.domain.repository.UserRepository;
import com.challenge.geosapiens.service_b.domain.usecase.user.CreateUserUseCase;
import com.challenge.geosapiens.service_b.infrastructure.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public User execute(UserDTO userDTO) {
        log.debug("Creating user with ID: {}", userDTO.getId());

        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);

        log.info("User created successfully with ID: {}", savedUser.getId());
        return savedUser;
    }
}
