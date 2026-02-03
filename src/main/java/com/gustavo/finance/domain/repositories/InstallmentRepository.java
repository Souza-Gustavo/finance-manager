package com.gustavo.finance.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustavo.finance.domain.entities.Installment;
import com.gustavo.finance.domain.entities.User;
import com.gustavo.finance.domain.enums.InstallmentStatus;

public interface InstallmentRepository extends JpaRepository<Installment, Long> {

    List<Installment> findByUser(User user);

    List<Installment> findByUserAndStatus(User user, InstallmentStatus status);

    List<Installment> findByUserAndStatusIn(User user, List<InstallmentStatus> statuses);

}
