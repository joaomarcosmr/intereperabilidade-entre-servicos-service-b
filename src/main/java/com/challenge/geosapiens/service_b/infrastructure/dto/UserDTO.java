package com.challenge.geosapiens.service_b.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {
    private UUID id;
    private String name;
    private String email;
}
