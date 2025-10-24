package com.challenge.geosapiens.service_b.infrastructure.usecase.order;

import com.challenge.geosapiens.service_b.application.exception.NotFoundException;
import com.challenge.geosapiens.service_b.domain.entity.Order;
import com.challenge.geosapiens.service_b.domain.repository.OrderRepository;
import com.challenge.geosapiens.service_b.domain.usecase.order.UpdateOrderUseCase;
import com.challenge.geosapiens.service_b.infrastructure.dto.OrderDTO;
import com.challenge.geosapiens.service_b.infrastructure.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateOrderUseCaseImpl implements UpdateOrderUseCase {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public Order execute(OrderDTO orderDTO) {
        log.info("[UpdateOrderUseCaseImpl] Starting order update with ID: {}", orderDTO.getId());

        Order order = orderRepository.findById(orderDTO.getId())
                .orElseThrow(() -> {
                    log.error("[UpdateOrderUseCaseImpl] Order not found with ID: {}", orderDTO.getId());
                    return new NotFoundException("Order not found with ID: " + orderDTO.getId());
                });

        orderDTO.setId(order.getId());
        Order updatedOrder = orderRepository.save(orderMapper.toEntity(orderDTO));

        log.info("[UpdateOrderUseCaseImpl] Order updated successfully with ID: {}", updatedOrder.getId());
        return updatedOrder;
    }
}
