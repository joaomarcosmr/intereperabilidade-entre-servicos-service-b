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
public class UpdateOrderUseCase {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Transactional
    public Order execute(OrderDTO orderDTO) {
        log.debug("Updating order with ID: {}", orderDTO.getId());

        Order order = orderRepository.findById(orderDTO.getId())
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderDTO.getId()));

        orderMapper.updateEntityFromDto(orderDTO, order);
        Order updatedOrder = orderRepository.save(order);

        log.info("Order updated successfully with ID: {}", updatedOrder.getId());
        return updatedOrder;
    }
}
