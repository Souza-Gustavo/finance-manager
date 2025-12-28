package com.gustavo.finance.application.controllers;

import com.gustavo.finance.application.dto.InstallmentRequest;
import com.gustavo.finance.application.dto.InstallmentResponse;
import com.gustavo.finance.domain.entities.Installment;
import com.gustavo.finance.domain.entities.User;
import com.gustavo.finance.domain.services.InstallmentService;
import com.gustavo.finance.domain.services.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/installments")
public class InstallmentController {

    private final InstallmentService installmentService;
    private final UserService userService;

    public InstallmentController(
            InstallmentService installmentService,
            UserService userService
    ) {
        this.installmentService = installmentService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<InstallmentResponse> create(
            @RequestBody InstallmentRequest request
    ) {
        User user = userService.findById(request.getUserId());

        Installment installment = new Installment();
        installment.setDescription(request.getDescription());
        installment.setTotalAmount(request.getTotalAmount());
        installment.setTotalParcels(request.getTotalParcels());
        installment.setStartDate(request.getStartDate());
        installment.setUser(user);

        Installment saved = installmentService.createInstallment(installment);

        InstallmentResponse response = new InstallmentResponse();
        response.setId(saved.getId());
        response.setDescription(saved.getDescription());
        response.setTotalAmount(saved.getTotalAmount());
        response.setTotalParcels(saved.getTotalParcels());
        response.setStartDate(saved.getStartDate());
        response.setStatus(saved.getStatus().name());
        response.setCreatedAt(saved.getCreatedAt());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
