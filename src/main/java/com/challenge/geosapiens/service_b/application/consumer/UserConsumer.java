package com.challenge.geosapiens.service_b.application.consumer;

import com.challenge.geosapiens.service_b.application.config.RabbitMQConfig;
import com.challenge.geosapiens.service_b.domain.usecase.user.CreateUserUseCase;
import com.challenge.geosapiens.service_b.domain.usecase.user.DeleteUserUseCase;
import com.challenge.geosapiens.service_b.domain.usecase.user.UpdateUserUseCase;
import com.challenge.geosapiens.service_b.infrastructure.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserConsumer {

    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    @RabbitListener(queues = RabbitMQConfig.USER_CREATE_QUEUE)
    public void consumeUserCreate(UserDTO userDTO) {
        log.info("[UserConsumer] Received CREATE user from queue: {}", userDTO);

        try {
            createUserUseCase.execute(userDTO);
            log.info("[UserConsumer] CREATE user processed successfully");
        } catch (Exception e) {
            log.error("[UserConsumer] Error creating user: {}", e.getMessage(), e);
            throw e;
        }
    }

    @RabbitListener(queues = RabbitMQConfig.USER_UPDATE_QUEUE)
    public void consumeUserUpdate(UserDTO userDTO) {
        log.info("[UserConsumer] Received UPDATE user from queue: {}", userDTO);

        try {
            updateUserUseCase.execute(userDTO);
            log.info("[UserConsumer] UPDATE user processed successfully");
        } catch (Exception e) {
            log.error("[UserConsumer] Error updating user: {}", e.getMessage(), e);
            throw e;
        }
    }

    @RabbitListener(queues = RabbitMQConfig.USER_DELETE_QUEUE)
    public void consumeUserDelete(UUID id) {
        log.info("[UserConsumer] Received DELETE user from queue: {}", id);

        try {
            deleteUserUseCase.execute(id);
            log.info("[UserConsumer] DELETE user processed successfully");
        } catch (Exception e) {
            log.error("[UserConsumer] Error deleting user: {}", e.getMessage(), e);
            throw e;
        }
    }
}
