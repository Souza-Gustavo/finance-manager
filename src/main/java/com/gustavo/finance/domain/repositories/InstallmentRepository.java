package com.gustavo.finance.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustavo.finance.domain.entities.Installment;
import com.gustavo.finance.domain.entities.User;
import com.gustavo.finance.domain.enums.InstallmentStatus;

import java.util.List;

public interface InstallmentRepository extends JpaRepository<Installment, Long> {

    List<Installment> findByUserAndStatus(User user, InstallmentStatus status);
    
}
