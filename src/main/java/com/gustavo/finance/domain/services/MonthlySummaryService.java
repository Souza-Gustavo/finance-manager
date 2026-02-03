package com.gustavo.finance.domain.services;

import org.springframework.stereotype.Service;
import java.util.List;
import java.math.BigDecimal;

import com.gustavo.finance.application.dto.MonthlySummaryDTO;
import com.gustavo.finance.domain.entities.User;
import com.gustavo.finance.domain.entities.Installment;
import com.gustavo.finance.domain.enums.InstallmentStatus;
import com.gustavo.finance.domain.entities.InstallmentParcel;
import com.gustavo.finance.domain.repositories.InstallmentRepository;

@Service
public class MonthlySummaryService {

    private final InstallmentRepository installmentRepository;
    private final InstallmentParcelRepository parcelRepository;

    public MonthlySummaryService(
            InstallmentRepository installmentRepository,
            InstallmentParcelRepository parcelRepository
    ) {
        this.installmentRepository = installmentRepository;
        this.parcelRepository = parcelRepository;
    }

    public MonthlySummaryDTO gerarResumoMensal(User user, int year, int month) {

        List<InstallmentStatus> statuses = List.of(
            InstallmentStatus.ACTIVE,
            InstallmentStatus.FINISHED
        );

        List<Installment> installments =
            installmentRepository.findByUserAndStatusIn(user, statuses);

        BigDecimal totalDoMes = installments.stream()
            .flatMap(installment ->
                parcelRepository.findByInstallment(installment).stream()
            )
            .filter(parcel ->
                parcel.getDueDate().getYear() == year &&
                parcel.getDueDate().getMonthValue() == month
            )
            .map(InstallmentParcel::getAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new MonthlySummaryDTO(year, month, totalDoMes);
    }

    public List<CategoryMonthlySummaryDTO> gerarResumoPorCategoria(
            User user,
            int year,
            int month
    ) {
        return null;
    }

}