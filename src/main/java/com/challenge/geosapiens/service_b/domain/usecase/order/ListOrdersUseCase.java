package com.challenge.geosapiens.service_b.domain.usecase.order;

import com.challenge.geosapiens.service_b.domain.entity.Order;

import java.util.List;

public interface ListOrdersUseCase {

    List<Order> execute();

}
