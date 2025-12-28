package com.gustavo.finance.domain.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gustavo.finance.domain.entities.Installment;
import com.gustavo.finance.domain.entities.InstallmentParcel;
import com.gustavo.finance.domain.repositories.InstallmentParcelRepository;
import com.gustavo.finance.domain.repositories.InstallmentRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;


@Service
public class InstallmentService {

    private final InstallmentRepository installmentRepository;
    private final InstallmentParcelRepository installmentParcelRepository;

    public InstallmentService(
            InstallmentRepository installmentRepository,
            InstallmentParcelRepository installmentParcelRepository
    ) {
        this.installmentRepository = installmentRepository;
        this.installmentParcelRepository = installmentParcelRepository;
    }

    @Transactional
    public Installment createInstallment(Installment installment) {
        Installment savedInstallment = installmentRepository.save(installment);

        generateParcels(savedInstallment);

        return savedInstallment;
    }

    private void generateParcels(Installment installment) {
        BigDecimal parcelValue = installment.getTotalAmount()
                .divide(
                    BigDecimal.valueOf(installment.getTotalParcels()),
                        2,
                        RoundingMode.HALF_UP
                );


        for (int i = 1; i <= installment.getTotalParcels(); i++) {
            InstallmentParcel parcel = new InstallmentParcel();
            parcel.setParcelNumber(i);
            parcel.setAmount(parcelValue);
            parcel.setDueDate(
                    installment.getStartDate().plusMonths(i - 1)
            );
            parcel.setInstallment(installment);

            installmentParcelRepository.save(parcel);
        }
    }
}
