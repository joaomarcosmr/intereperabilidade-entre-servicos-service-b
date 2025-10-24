package com.challenge.geosapiens.service_b.infrastructure.usecase.order;

import com.challenge.geosapiens.service_b.application.exception.AlreadyExistsException;
import com.challenge.geosapiens.service_b.domain.entity.Order;
import com.challenge.geosapiens.service_b.domain.repository.OrderRepository;
import com.challenge.geosapiens.service_b.domain.usecase.order.CreateOrderUseCase;
import com.challenge.geosapiens.service_b.infrastructure.dto.OrderDTO;
import com.challenge.geosapiens.service_b.infrastructure.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateOrderUseCaseImpl implements CreateOrderUseCase {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    @Transactional
    @SneakyThrows
    public Order execute(OrderDTO orderDTO) {
        log.info("[CreateOrderUseCaseImpl] Starting order creation with ID: {}", orderDTO.getId());

        if(orderRepository.existsById(orderDTO.getId())) {
            log.error("[CreateOrderUseCaseImpl] Order already exists with ID: {}", orderDTO.getId());
            throw new AlreadyExistsException("Order already exists with ID: " + orderDTO.getId());
        }

        Order savedOrder = orderRepository.save(orderMapper.toEntity(orderDTO));

        // Pra testar retry na fila e rollback dos dados salvos
//        if(true){
//            throw new Exception("testing retry...");
//        }

        log.info("[CreateOrderUseCaseImpl] Order created successfully with ID: {}", savedOrder.getId());
        return savedOrder;
    }
}
