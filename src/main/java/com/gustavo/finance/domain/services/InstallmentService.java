package com.gustavo.finance.domain.services;

import org.springframework.stereotype.Service;

import com.gustavo.finance.application.dto.InstallmentRequestDTO;
import com.gustavo.finance.domain.entities.Installment;
import com.gustavo.finance.domain.entities.InstallmentParcel;
import com.gustavo.finance.domain.entities.User;
import com.gustavo.finance.domain.repositories.InstallmentRepository;
import com.gustavo.finance.domain.repositories.InstallmentParcelRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class InstallmentService {

    private final InstallmentRepository installmentRepository;
    private final InstallmentParcelRepository parcelRepository;

    public InstallmentService(
            InstallmentRepository installmentRepository,
            InstallmentParcelRepository parcelRepository
    ) {
        this.installmentRepository = installmentRepository;
        this.parcelRepository = parcelRepository;
    }

    public Installment criar(InstallmentRequestDTO dto, User user) {

        Installment installment = new Installment();
        installment.setDescription(dto.getDescription());
        installment.setTotalAmount(dto.getTotalAmount());
        installment.setTotalParcels(dto.getTotalParcels());
        installment.setStartDate(dto.getStartDate());
        installment.setUser(user);

        Installment salvo = installmentRepository.save(installment);

        BigDecimal valorParcela =
                dto.getTotalAmount().divide(
                        BigDecimal.valueOf(dto.getTotalParcels())
                );

        for (int i = 1; i <= dto.getTotalParcels(); i++) {
            InstallmentParcel parcel = new InstallmentParcel();
            parcel.setParcelNumber(i);
            parcel.setAmount(valorParcela);
            parcel.setDueDate(dto.getStartDate().plusMonths(i - 1));
            parcel.setInstallment(salvo);

            parcelRepository.save(parcel);
        }

        return salvo;
    }
}
