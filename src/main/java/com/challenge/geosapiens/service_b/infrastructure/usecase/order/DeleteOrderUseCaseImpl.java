package com.challenge.geosapiens.service_b.infrastructure.usecase.order;

import com.challenge.geosapiens.service_b.application.exception.NotFoundException;
import com.challenge.geosapiens.service_b.domain.entity.Order;
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
        log.info("[DeleteOrderUseCaseImpl] Starting order deletion with ID: {}", orderId);

        if(!orderRepository.existsById(orderId)) {
            log.error("[DeleteOrderUseCaseImpl] Order not found with ID: {}", orderId);
            throw new NotFoundException("Order not found with ID: " + orderId);
        }

        orderRepository.deleteById(orderId);

        log.info("[DeleteOrderUseCaseImpl] Order deleted successfully with ID: {}", orderId);
    }
}
