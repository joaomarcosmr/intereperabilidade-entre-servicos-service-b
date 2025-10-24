package com.challenge.geosapiens.service_b.infrastructure.usecase.user;

import com.challenge.geosapiens.service_b.domain.entity.User;
import com.challenge.geosapiens.service_b.domain.repository.UserRepository;
import com.challenge.geosapiens.service_b.domain.usecase.user.ListUsersUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ListUsersUseCaseImpl implements ListUsersUseCase {

    private final UserRepository userRepository;

    @Override
    public List<User> execute() {
        log.info("[ListUsersUseCaseImpl] Starting users listing");
        List<User> users = userRepository.findAll();
        log.info("[ListUsersUseCaseImpl] Found {} users", users.size());
        return users;
    }
}

