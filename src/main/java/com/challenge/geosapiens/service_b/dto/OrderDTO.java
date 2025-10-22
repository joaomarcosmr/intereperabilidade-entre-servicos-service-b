package com.challenge.geosapiens.service_b.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO implements Serializable {
    private Long id;
    private String description;
    private Double value;
    private Long userId;
    private Long deliveryPersonId;
}
