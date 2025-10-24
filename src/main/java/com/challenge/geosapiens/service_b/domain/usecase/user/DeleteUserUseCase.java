package com.challenge.geosapiens.service_b.domain.usecase.user;

import java.util.UUID;

public interface DeleteUserUseCase {
    void execute(UUID userId);
}
