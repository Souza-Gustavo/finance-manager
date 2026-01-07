package com.gustavo.finance.domain.services;

import org.springframework.stereotype.Service;

import com.gustavo.finance.application.dto.InstallmentParcelResponseDTO;
import com.gustavo.finance.application.dto.InstallmentRequestDTO;
import com.gustavo.finance.domain.entities.Installment;
import com.gustavo.finance.domain.entities.InstallmentParcel;
import com.gustavo.finance.domain.entities.User;
import com.gustavo.finance.domain.repositories.InstallmentParcelRepository;
import com.gustavo.finance.domain.repositories.InstallmentRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstallmentService {

    private final InstallmentRepository installmentRepository;
    private final InstallmentParcelRepository parcelRepository;

    public List<Installment> listarDoUsuario(User user) {
    return installmentRepository.findByUser(user);
}
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

    public List<InstallmentParcelResponseDTO> listarParcelas(Long installmentId, User user) {

    Installment installment = installmentRepository.findById(installmentId)
            .orElseThrow(() -> new RuntimeException("Parcelamento não encontrado"));

    if (!installment.getUser().getId().equals(user.getId())) {
        throw new RuntimeException("Acesso negado");
    }

    return parcelRepository.findByInstallment(installment)
            .stream()
            .map(parcel -> {
                InstallmentParcelResponseDTO dto =
                        new InstallmentParcelResponseDTO();

                dto.setId(parcel.getId());
                dto.setParcelNumber(parcel.getParcelNumber());
                dto.setAmount(parcel.getAmount());
                dto.setDueDate(parcel.getDueDate());
                dto.setPaid(parcel.getPaid());
                dto.setPaymentDate(parcel.getPaymentDate());

                return dto;
            })
            .collect(Collectors.toList());
}
}
