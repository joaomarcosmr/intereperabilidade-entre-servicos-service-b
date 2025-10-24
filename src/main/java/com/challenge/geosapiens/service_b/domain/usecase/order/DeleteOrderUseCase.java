package com.challenge.geosapiens.service_b.domain.usecase.order;

import java.util.UUID;

public interface DeleteOrderUseCase {
    void execute(UUID orderId);
}
