package com.challenge.geosapiens.service_b.domain.repository;

import com.challenge.geosapiens.service_b.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
}
