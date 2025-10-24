package com.challenge.geosapiens.service_b.domain.usecase.user;

import com.challenge.geosapiens.service_b.domain.entity.User;
import com.challenge.geosapiens.service_b.infrastructure.dto.UserDTO;

public interface UpdateUserUseCase {
    User execute(UserDTO userDTO);
}
