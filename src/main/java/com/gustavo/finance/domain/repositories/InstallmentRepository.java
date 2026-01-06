package com.gustavo.finance.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gustavo.finance.domain.entities.Installment;

public interface InstallmentRepository extends JpaRepository<Installment, Long> {
}
