package com.gustavo.finance.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustavo.finance.domain.entities.Installment;
import com.gustavo.finance.domain.entities.InstallmentParcel;

public interface InstallmentParcelRepository
        extends JpaRepository<InstallmentParcel, Long> {

    List<InstallmentParcel> findByInstallment(Installment installment);

    boolean existsByInstallmentAndPaidFalse(Installment installment);

    void deleteByInstallment(Installment installment);
}
