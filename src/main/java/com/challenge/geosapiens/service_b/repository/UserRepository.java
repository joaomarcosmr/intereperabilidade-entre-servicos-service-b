package com.challenge.geosapiens.service_b.repository;

import com.challenge.geosapiens.service_b.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
