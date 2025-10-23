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
public class UpdateDeliveryPersonUseCase {

    private final DeliveryPersonRepository deliveryPersonRepository;

    @Transactional
    public DeliveryPerson execute(DeliveryPersonDTO deliveryPersonDTO) {
        log.debug("Updating delivery person with ID: {}", deliveryPersonDTO.getId());

        DeliveryPerson deliveryPerson = deliveryPersonRepository.findById(deliveryPersonDTO.getId())
                .orElseThrow(() -> new RuntimeException("DeliveryPerson not found with ID: " + deliveryPersonDTO.getId()));

        deliveryPerson.setName(deliveryPersonDTO.getName());
        deliveryPerson.setPhone(deliveryPersonDTO.getPhone());
        deliveryPerson.setUpdatedAt(LocalDateTime.now());

        DeliveryPerson updatedDeliveryPerson = deliveryPersonRepository.save(deliveryPerson);

        log.info("DeliveryPerson updated successfully with ID: {}", updatedDeliveryPerson.getId());
        return updatedDeliveryPerson;
    }
}
