package com.challenge.geosapiens.service_b.application.usecase.order;

import com.challenge.geosapiens.service_b.domain.entity.Order;
import com.challenge.geosapiens.service_b.domain.repository.OrderRepository;
import com.challenge.geosapiens.service_b.infrastructure.dto.OrderDTO;
import com.challenge.geosapiens.service_b.infrastructure.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateOrderUseCase {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Transactional
    public Order execute(OrderDTO orderDTO) {
        log.debug("Creating order with ID: {}", orderDTO.getId());

        Order savedOrder = orderRepository.save(orderMapper.toEntity(orderDTO));

        log.info("Order created successfully with ID: {}", savedOrder.getId());
        return savedOrder;
    }
}
