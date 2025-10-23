package com.challenge.geosapiens.service_b.application.consumer;

import com.challenge.geosapiens.service_b.application.config.RabbitMQConfig;
import com.challenge.geosapiens.service_b.application.usecase.deliveryperson.CreateDeliveryPersonUseCase;
import com.challenge.geosapiens.service_b.application.usecase.deliveryperson.DeleteDeliveryPersonUseCase;
import com.challenge.geosapiens.service_b.application.usecase.deliveryperson.UpdateDeliveryPersonUseCase;
import com.challenge.geosapiens.service_b.infrastructure.dto.DeliveryPersonDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeliveryPersonConsumer {

    private final CreateDeliveryPersonUseCase createDeliveryPersonUseCase;
    private final UpdateDeliveryPersonUseCase updateDeliveryPersonUseCase;
    private final DeleteDeliveryPersonUseCase deleteDeliveryPersonUseCase;

    @RabbitListener(queues = RabbitMQConfig.DELIVERY_PERSON_CREATE_QUEUE)
    public void consumeDeliveryPersonCreate(DeliveryPersonDTO deliveryPersonDTO) {
        log.info("Received CREATE delivery person from queue: {}", deliveryPersonDTO);

        try {
            createDeliveryPersonUseCase.execute(deliveryPersonDTO);
        } catch (Exception e) {
            log.error("Error creating delivery person: {}", e.getMessage(), e);
            throw e;
        }
    }

    @RabbitListener(queues = RabbitMQConfig.DELIVERY_PERSON_UPDATE_QUEUE)
    public void consumeDeliveryPersonUpdate(DeliveryPersonDTO deliveryPersonDTO) {
        log.info("Received UPDATE delivery person from queue: {}", deliveryPersonDTO);

        try {
            updateDeliveryPersonUseCase.execute(deliveryPersonDTO);
        } catch (Exception e) {
            log.error("Error updating delivery person: {}", e.getMessage(), e);
            throw e;
        }
    }

    @RabbitListener(queues = RabbitMQConfig.DELIVERY_PERSON_DELETE_QUEUE)
    public void consumeDeliveryPersonDelete(DeliveryPersonDTO deliveryPersonDTO) {
        log.info("Received DELETE delivery person from queue: {}", deliveryPersonDTO);

        try {
            deleteDeliveryPersonUseCase.execute(deliveryPersonDTO.getId());
        } catch (Exception e) {
            log.error("Error deleting delivery person: {}", e.getMessage(), e);
            throw e;
        }
    }
}
