package com.challenge.geosapiens.service_b.application.usecase.deliveryperson;

import com.challenge.geosapiens.service_b.domain.entity.DeliveryPerson;
import com.challenge.geosapiens.service_b.domain.repository.DeliveryPersonRepository;
import com.challenge.geosapiens.service_b.infrastructure.dto.DeliveryPersonDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateDeliveryPersonUseCase {

    private final DeliveryPersonRepository deliveryPersonRepository;

    @Transactional
    public DeliveryPerson execute(DeliveryPersonDTO deliveryPersonDTO) {
        log.debug("Creating delivery person with ID: {}", deliveryPersonDTO.getId());

        DeliveryPerson deliveryPerson = new DeliveryPerson();
        deliveryPerson.setId(deliveryPersonDTO.getId());
        deliveryPerson.setName(deliveryPersonDTO.getName());
        deliveryPerson.setPhone(deliveryPersonDTO.getPhone());
        deliveryPerson.setCreatedAt(LocalDateTime.now());
        deliveryPerson.setUpdatedAt(LocalDateTime.now());

        DeliveryPerson savedDeliveryPerson = deliveryPersonRepository.save(deliveryPerson);

        log.info("DeliveryPerson created successfully with ID: {}", savedDeliveryPerson.getId());
        return savedDeliveryPerson;
    }
}
