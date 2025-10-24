package com.challenge.geosapiens.service_b.infrastructure.usecase.order;

import com.challenge.geosapiens.service_b.domain.entity.Order;
import com.challenge.geosapiens.service_b.domain.repository.OrderRepository;
import com.challenge.geosapiens.service_b.domain.usecase.order.ListOrdersUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ListOrdersUseCaseImpl implements ListOrdersUseCase {

    private final OrderRepository orderRepository;

    @Override
    public List<Order> execute() {
        log.info("[ListOrdersUseCaseImpl] Starting orders listing");
        List<Order> orders = orderRepository.findAllOrders();
        log.info("[ListOrdersUseCaseImpl] Found {} orders", orders.size());
        return orders;
    }
}
