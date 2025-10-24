package com.challenge.geosapiens.service_b.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO implements Serializable {
    private UUID id;
    private String description;
    private Double value;
    private UUID userId;
    private String deliveryPersonName;
    private String deliveryPersonPhone;
}
