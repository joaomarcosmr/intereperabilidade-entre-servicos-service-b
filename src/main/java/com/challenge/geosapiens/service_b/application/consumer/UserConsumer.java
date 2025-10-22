package com.challenge.geosapiens.service_b.application.consumer;

import com.challenge.geosapiens.service_b.application.config.RabbitMQConfig;
import com.challenge.geosapiens.service_b.dto.UserDTO;
import com.challenge.geosapiens.service_b.domain.entity.User;
import com.challenge.geosapiens.service_b.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserConsumer {

    private final UserRepository userRepository;

    @RabbitListener(queues = RabbitMQConfig.USER_CREATE_QUEUE)
    public void consumeUserCreate(UserDTO userDTO) {
        log.info("Received CREATE user from queue: {}", userDTO);

        try {
            User user = new User();
            user.setId(userDTO.getId());
            user.setName(userDTO.getName());
            user.setEmail(userDTO.getEmail());

            User savedUser = userRepository.save(user);
            log.info("User created successfully with ID: {}", savedUser.getId());
        } catch (Exception e) {
            log.error("Error creating user: {}", e.getMessage(), e);
            throw e;
        }
    }

    @RabbitListener(queues = RabbitMQConfig.USER_UPDATE_QUEUE)
    public void consumeUserUpdate(UserDTO userDTO) {
        log.info("Received UPDATE user from queue: {}", userDTO);

        try {
            User user = userRepository.findById(userDTO.getId())
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + userDTO.getId()));

            user.setName(userDTO.getName());
            user.setEmail(userDTO.getEmail());

            User updatedUser = userRepository.save(user);
            log.info("User updated successfully with ID: {}", updatedUser.getId());
        } catch (Exception e) {
            log.error("Error updating user: {}", e.getMessage(), e);
            throw e;
        }
    }

    @RabbitListener(queues = RabbitMQConfig.USER_DELETE_QUEUE)
    public void consumeUserDelete(UserDTO userDTO) {
        log.info("Received DELETE user from queue: {}", userDTO);

        try {
            userRepository.deleteById(userDTO.getId());
            log.info("User deleted successfully with ID: {}", userDTO.getId());
        } catch (Exception e) {
            log.error("Error deleting user: {}", e.getMessage(), e);
            throw e;
        }
    }
}
