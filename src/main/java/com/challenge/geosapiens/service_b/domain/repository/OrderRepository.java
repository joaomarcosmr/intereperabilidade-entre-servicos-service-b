package com.challenge.geosapiens.service_b.domain.repository;

import com.challenge.geosapiens.service_b.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
