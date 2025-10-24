package com.challenge.geosapiens.service_b.infrastructure.usecase.user;

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
        log.debug("Deleting user with ID: {}", userId);

        userRepository.deleteById(userId);

        log.info("User deleted successfully with ID: {}", userId);
    }
}
