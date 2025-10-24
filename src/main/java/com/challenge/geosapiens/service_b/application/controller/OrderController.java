package com.challenge.geosapiens.service_b.application.controller;

import com.challenge.geosapiens.service_b.domain.usecase.order.ListOrdersUseCase;
import com.challenge.geosapiens.service_b.infrastructure.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final ListOrdersUseCase listOrdersUseCase;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getOrders() {
        return ResponseEntity.ok(listOrdersUseCase.execute());
    }
}
