package com.challenge.geosapiens.service_b.application.usecase.user;

import com.challenge.geosapiens.service_b.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteUserUseCase {

    private final UserRepository userRepository;

    @Transactional
    public void execute(Long userId) {
        log.debug("Deleting user with ID: {}", userId);

        userRepository.deleteById(userId);

        log.info("User deleted successfully with ID: {}", userId);
    }
}
