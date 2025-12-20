package com.gustavo.application.controllers;

import com.gustavo.domain.entities.Installment;
import com.gustavo.domain.services.InstallmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/installments")
public class InstallmentController {

    private final InstallmentService installmentService;

    public InstallmentController(InstallmentService installmentService) {
        this.installmentService = installmentService;
    }

    @PostMapping
    public ResponseEntity<Installment> create(@RequestBody Installment installment) {
        Installment saved = installmentService.createInstallment(installment);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
