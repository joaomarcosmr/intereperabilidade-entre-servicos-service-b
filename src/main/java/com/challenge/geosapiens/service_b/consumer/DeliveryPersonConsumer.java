package com.challenge.geosapiens.service_b.consumer;

import com.challenge.geosapiens.service_b.config.RabbitMQConfig;
import com.challenge.geosapiens.service_b.dto.DeliveryPersonDTO;
import com.challenge.geosapiens.service_b.entity.DeliveryPerson;
import com.challenge.geosapiens.service_b.repository.DeliveryPersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeliveryPersonConsumer {

    private final DeliveryPersonRepository deliveryPersonRepository;

    @RabbitListener(queues = RabbitMQConfig.DELIVERY_PERSON_CREATE_QUEUE)
    public void consumeDeliveryPersonCreate(DeliveryPersonDTO deliveryPersonDTO) {
        log.info("Received CREATE delivery person from queue: {}", deliveryPersonDTO);

        try {
            DeliveryPerson deliveryPerson = new DeliveryPerson();
            deliveryPerson.setId(deliveryPersonDTO.getId());
            deliveryPerson.setName(deliveryPersonDTO.getName());
            deliveryPerson.setEmail(deliveryPersonDTO.getEmail());

            DeliveryPerson savedDeliveryPerson = deliveryPersonRepository.save(deliveryPerson);
            log.info("DeliveryPerson created successfully with ID: {}", savedDeliveryPerson.getId());
        } catch (Exception e) {
            log.error("Error creating delivery person: {}", e.getMessage(), e);
            throw e;
        }
    }

    @RabbitListener(queues = RabbitMQConfig.DELIVERY_PERSON_UPDATE_QUEUE)
    public void consumeDeliveryPersonUpdate(DeliveryPersonDTO deliveryPersonDTO) {
        log.info("Received UPDATE delivery person from queue: {}", deliveryPersonDTO);

        try {
            DeliveryPerson deliveryPerson = deliveryPersonRepository.findById(deliveryPersonDTO.getId())
                    .orElseThrow(() -> new RuntimeException("DeliveryPerson not found with ID: " + deliveryPersonDTO.getId()));

            deliveryPerson.setName(deliveryPersonDTO.getName());
            deliveryPerson.setEmail(deliveryPersonDTO.getEmail());

            DeliveryPerson updatedDeliveryPerson = deliveryPersonRepository.save(deliveryPerson);
            log.info("DeliveryPerson updated successfully with ID: {}", updatedDeliveryPerson.getId());
        } catch (Exception e) {
            log.error("Error updating delivery person: {}", e.getMessage(), e);
            throw e;
        }
    }

    @RabbitListener(queues = RabbitMQConfig.DELIVERY_PERSON_DELETE_QUEUE)
    public void consumeDeliveryPersonDelete(DeliveryPersonDTO deliveryPersonDTO) {
        log.info("Received DELETE delivery person from queue: {}", deliveryPersonDTO);

        try {
            deliveryPersonRepository.deleteById(deliveryPersonDTO.getId());
            log.info("DeliveryPerson deleted successfully with ID: {}", deliveryPersonDTO.getId());
        } catch (Exception e) {
            log.error("Error deleting delivery person: {}", e.getMessage(), e);
            throw e;
        }
    }
}
