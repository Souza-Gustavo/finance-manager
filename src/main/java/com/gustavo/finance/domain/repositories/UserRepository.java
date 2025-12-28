package com.gustavo.finance.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustavo.finance.domain.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByEmail(String email);
    
}
