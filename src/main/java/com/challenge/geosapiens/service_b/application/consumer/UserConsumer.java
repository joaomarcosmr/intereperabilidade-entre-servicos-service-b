package com.challenge.geosapiens.service_b.application.consumer;

import com.challenge.geosapiens.service_b.application.config.RabbitMQConfig;
import com.challenge.geosapiens.service_b.application.usecase.user.CreateUserUseCase;
import com.challenge.geosapiens.service_b.application.usecase.user.DeleteUserUseCase;
import com.challenge.geosapiens.service_b.application.usecase.user.UpdateUserUseCase;
import com.challenge.geosapiens.service_b.infrastructure.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserConsumer {

    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    @RabbitListener(queues = RabbitMQConfig.USER_CREATE_QUEUE)
    public void consumeUserCreate(UserDTO userDTO) {
        log.info("Received CREATE user from queue: {}", userDTO);

        try {
            createUserUseCase.execute(userDTO);
        } catch (Exception e) {
            log.error("Error creating user: {}", e.getMessage(), e);
            throw e;
        }
    }

    @RabbitListener(queues = RabbitMQConfig.USER_UPDATE_QUEUE)
    public void consumeUserUpdate(UserDTO userDTO) {
        log.info("Received UPDATE user from queue: {}", userDTO);

        try {
            updateUserUseCase.execute(userDTO);
        } catch (Exception e) {
            log.error("Error updating user: {}", e.getMessage(), e);
            throw e;
        }
    }

    @RabbitListener(queues = RabbitMQConfig.USER_DELETE_QUEUE)
    public void consumeUserDelete(UserDTO userDTO) {
        log.info("Received DELETE user from queue: {}", userDTO);

        try {
            deleteUserUseCase.execute(userDTO.getId());
        } catch (Exception e) {
            log.error("Error deleting user: {}", e.getMessage(), e);
            throw e;
        }
    }
}
