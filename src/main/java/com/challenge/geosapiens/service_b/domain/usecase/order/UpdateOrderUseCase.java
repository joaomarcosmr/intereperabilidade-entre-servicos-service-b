package com.challenge.geosapiens.service_b.domain.usecase.order;

import com.challenge.geosapiens.service_b.domain.entity.Order;
import com.challenge.geosapiens.service_b.infrastructure.dto.OrderDTO;

public interface UpdateOrderUseCase {
    Order execute(OrderDTO orderDTO);
}
