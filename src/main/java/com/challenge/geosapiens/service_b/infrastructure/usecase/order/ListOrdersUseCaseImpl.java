package com.challenge.geosapiens.service_b.infrastructure.usecase.order;

import com.challenge.geosapiens.service_b.domain.entity.Order;
import com.challenge.geosapiens.service_b.domain.repository.OrderRepository;
import com.challenge.geosapiens.service_b.domain.usecase.order.ListOrdersUseCase;
import com.challenge.geosapiens.service_b.infrastructure.dto.OrderDTO;
import com.challenge.geosapiens.service_b.infrastructure.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListOrdersUseCaseImpl implements ListOrdersUseCase {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public List<Order> execute() {
        return orderRepository.findAllOrders();
    }
}
