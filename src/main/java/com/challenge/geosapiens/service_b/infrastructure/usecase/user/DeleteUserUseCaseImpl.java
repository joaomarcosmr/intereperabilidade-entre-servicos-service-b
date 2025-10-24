package com.challenge.geosapiens.service_b.infrastructure.usecase.user;

import com.challenge.geosapiens.service_b.application.exception.NotFoundException;
import com.challenge.geosapiens.service_b.domain.entity.User;
import com.challenge.geosapiens.service_b.domain.repository.UserRepository;
import com.challenge.geosapiens.service_b.domain.usecase.user.DeleteUserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public void execute(UUID userId) {
        log.info("[DeleteUserUseCaseImpl] Starting user deletion with ID: {}", userId);

        if(!userRepository.existsById(userId)) {
            log.error("[DeleteUserUseCaseImpl] User not found with ID: {}", userId);
            throw new NotFoundException("User not found with ID: " + userId);
        }

        userRepository.deleteById(userId);

        log.info("[DeleteUserUseCaseImpl] User deleted successfully with ID: {}", userId);
    }
}
