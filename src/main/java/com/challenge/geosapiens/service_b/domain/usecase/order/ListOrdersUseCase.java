package com.challenge.geosapiens.service_b.domain.usecase.order;

import com.challenge.geosapiens.service_b.infrastructure.dto.OrderDTO;

import java.util.List;

public interface ListOrdersUseCase {

    List<OrderDTO> execute();

}
