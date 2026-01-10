package com.gustavo.finance.application.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.gustavo.finance.domain.entities.InstallmentParcel;
import com.gustavo.finance.domain.entities.User;
import com.gustavo.finance.domain.services.InstallmentParcelService;

@RestController
@RequestMapping("/installments/parcels")
public class InstallmentParcelController {

    private final InstallmentParcelService parcelService;

    public InstallmentParcelController(InstallmentParcelService parcelService) {
        this.parcelService = parcelService;
    }

    @PatchMapping("/{id}/pay")
    public ResponseEntity<InstallmentParcel> pagar(
            @PathVariable Long id,
            @AuthenticationPrincipal User user
    ) {
        InstallmentParcel parcel = parcelService.pagarParcela(id, user);
        return ResponseEntity.ok(parcel);
    }
}
