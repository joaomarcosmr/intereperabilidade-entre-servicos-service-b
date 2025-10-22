package com.challenge.geosapiens.service_b.domain.repository;

import com.challenge.geosapiens.service_b.domain.entity.DeliveryPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryPersonRepository extends JpaRepository<DeliveryPerson, Long> {
}
