package com.challenge.geosapiens.service_b.application.usecase.deliveryperson;

import com.challenge.geosapiens.service_b.domain.repository.DeliveryPersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteDeliveryPersonUseCase {

    private final DeliveryPersonRepository deliveryPersonRepository;

    @Transactional
    public void execute(Long deliveryPersonId) {
        log.debug("Deleting delivery person with ID: {}", deliveryPersonId);

        deliveryPersonRepository.deleteById(deliveryPersonId);

        log.info("DeliveryPerson deleted successfully with ID: {}", deliveryPersonId);
    }
}
