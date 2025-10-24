package com.challenge.geosapiens.service_b.application.controller;

import com.challenge.geosapiens.service_b.domain.entity.Order;
import com.challenge.geosapiens.service_b.domain.usecase.order.ListOrdersUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final ListOrdersUseCase listOrdersUseCase;

    @GetMapping
    public ResponseEntity<List<Order>> getOrders() {
        log.info("[OrderController] Listing all orders");
        List<Order> orders = listOrdersUseCase.execute();
        return ResponseEntity.ok(orders);
    }
}
