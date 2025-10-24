package com.challenge.geosapiens.service_b.infrastructure.usecase.order;

import com.challenge.geosapiens.service_b.domain.repository.OrderRepository;
import com.challenge.geosapiens.service_b.domain.usecase.order.DeleteOrderUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteOrderUseCaseImpl implements DeleteOrderUseCase {

    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public void execute(UUID orderId) {
        log.debug("Deleting order with ID: {}", orderId);

        orderRepository.deleteById(orderId);

        log.info("Order deleted successfully with ID: {}", orderId);
    }
}
