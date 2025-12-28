package com.gustavo.finance.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustavo.finance.domain.entities.Installment;
import com.gustavo.finance.domain.entities.InstallmentParcel;

import java.time.LocalDate;
import java.util.List;

public interface InstallmentParcelRepository extends JpaRepository<InstallmentParcel, Long> {

    List<InstallmentParcel> findByInstallment(Installment installment);

    List<InstallmentParcel> findByDueDateBetween(
            LocalDate startDate,
            LocalDate endDate
    );
}
