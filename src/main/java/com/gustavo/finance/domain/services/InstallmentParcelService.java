package com.gustavo.finance.domain.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gustavo.finance.domain.entities.InstallmentParcel;
import com.gustavo.finance.domain.entities.User;
import com.gustavo.finance.domain.repositories.InstallmentParcelRepository;

import java.time.LocalDate;

@Service
public class InstallmentParcelService {

    private final InstallmentParcelRepository parcelRepository;

    public InstallmentParcelService(InstallmentParcelRepository parcelRepository) {
        this.parcelRepository = parcelRepository;
    }

    public InstallmentParcel pagarParcela(Long parcelId, User user) {

        InstallmentParcel parcel = parcelRepository.findById(parcelId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Parcela não encontrada"
                ));

        if (!parcel.getInstallment().getUser().getId().equals(user.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "Você não pode pagar esta parcela"
            );
        }

        if (parcel.isPaid()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Parcela já está paga"
            );
        }

        parcel.setPaid(true);
        parcel.setPaymentDate(LocalDate.now());

        return parcelRepository.save(parcel);
    }
}