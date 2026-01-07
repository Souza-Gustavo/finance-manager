package com.gustavo.finance.application.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.gustavo.finance.application.dto.InstallmentRequestDTO;
import com.gustavo.finance.domain.entities.Installment;
import com.gustavo.finance.domain.entities.User;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
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
          @RequestBody InstallmentRequestDTO dto) {
             User user = (User) SecurityContextHolder
             .getContext()
             .getAuthentication()
             .getPrincipal();

    Installment installment = installmentService.criar(dto, user);
    return ResponseEntity.status(HttpStatus.CREATED).body(installment);
}

    @GetMapping
        public ResponseEntity<List<Installment>> listar(
         @AuthenticationPrincipal User user) {
            return ResponseEntity.ok(
            installmentService.listarDoUsuario(user)
    );
}
    
    @GetMapping("/{id}/parcels")
    public ResponseEntity<?> listarParcelas(
        @PathVariable Long id,
        @AuthenticationPrincipal User user) {

        return ResponseEntity.ok(
            installmentService.listarParcelas(id, user)
    );
}


}

