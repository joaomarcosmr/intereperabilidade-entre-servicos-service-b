package com.challenge.geosapiens.service_b.infrastructure.mapper;

import com.challenge.geosapiens.service_b.domain.entity.Order;
import com.challenge.geosapiens.service_b.infrastructure.dto.OrderDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    Order toEntity(OrderDTO orderDTO);

    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(OrderDTO orderDTO, @MappingTarget Order order);

    @Mapping(source = "userId", target = "userId")
    OrderDTO toDTO(Order order);
}
