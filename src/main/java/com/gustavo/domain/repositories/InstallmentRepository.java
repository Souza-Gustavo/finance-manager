package com.gustavo.domain.repositories;

import com.gustavo.domain.entities.Installment;
import com.gustavo.domain.entities.User;
import com.gustavo.domain.enums.InstallmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstallmentRepository extends JpaRepository<Installment, Long> {

    List<Installment> findByUserAndStatus(User user, InstallmentStatus status);
    
}
