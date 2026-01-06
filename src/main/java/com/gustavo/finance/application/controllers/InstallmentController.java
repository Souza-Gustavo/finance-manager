package com.gustavo.finance.application.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.gustavo.finance.application.dto.InstallmentRequestDTO;
import com.gustavo.finance.domain.entities.Installment;
import com.gustavo.finance.domain.entities.User;
import com.gustavo.finance.domain.services.InstallmentService;

@RestController
@RequestMapping("/installments")
public class InstallmentController {

    private final InstallmentService installmentService;

    public InstallmentController(InstallmentService installmentService) {
        this.installmentService = installmentService;
    }

    @PostMapping
    public ResponseEntity<Installment> criar(
            @RequestBody InstallmentRequestDTO dto,
            @AuthenticationPrincipal User user
    ) {
        Installment installment = installmentService.criar(dto, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(installment);
    }
}

