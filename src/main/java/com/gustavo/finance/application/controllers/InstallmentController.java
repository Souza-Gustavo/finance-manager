package com.gustavo.finance.application.controllers;

import com.gustavo.finance.application.dto.InstallmentParcelResponseDTO;
import com.gustavo.finance.application.dto.InstallmentRequestDTO;
import com.gustavo.finance.application.dto.InstallmentResponseDTO;
import com.gustavo.finance.domain.entities.Installment;
import com.gustavo.finance.domain.entities.User;
import com.gustavo.finance.domain.services.InstallmentService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/installments")
@CrossOrigin(origins = "http://localhost:5173")
public class InstallmentController {

    private final InstallmentService installmentService;

    public InstallmentController(InstallmentService installmentService) {
        this.installmentService = installmentService;
    }


    @PostMapping
    public ResponseEntity<InstallmentResponseDTO> criar(
            @Valid @RequestBody InstallmentRequestDTO dto,
            @AuthenticationPrincipal User user
    ) {
        Installment installment = installmentService.criar(dto, user);

        InstallmentResponseDTO response =
                new InstallmentResponseDTO(
                        installment.getId(),
                        installment.getDescription(),
                        installment.getTotalAmount(),
                        installment.getTotalParcels(),
                        installment.getStartDate(),
                        installment.getStatus().name(),
                        installment.getCategory() != null
                                ? installment.getCategory().getName()
                                : null
                );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping
    public ResponseEntity<List<InstallmentResponseDTO>> listar(
            @AuthenticationPrincipal User user
    ) {
        List<InstallmentResponseDTO> response =
                installmentService.listarDoUsuario(user)
                        .stream()
                        .map(installment ->
                                new InstallmentResponseDTO(
                                        installment.getId(),
                                        installment.getDescription(),
                                        installment.getTotalAmount(),
                                        installment.getTotalParcels(),
                                        installment.getStartDate(),
                                        installment.getStatus().name(),
                                        installment.getCategory() != null
                                                ? installment.getCategory().getName()
                                                : null
                                )
                        )
                        .toList();

        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}/parcels")
    public ResponseEntity<List<InstallmentParcelResponseDTO>> listarParcelas(
            @PathVariable Long id,
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(
                installmentService.listarParcelas(id, user)
        );
    }

    @PutMapping("/parcels/{parcelId}/pay")
        public ResponseEntity<Void> pagarParcela(
                @PathVariable Long parcelId,
                @AuthenticationPrincipal User user
        ) {
         installmentService.marcarParcelaComoPaga(parcelId, user);
         return ResponseEntity.noContent().build();
 }

        @GetMapping("/active")
                public ResponseEntity<List<InstallmentResponseDTO>> listarAtivos(
                 @AuthenticationPrincipal User user
) {
        List<InstallmentResponseDTO> response =
            installmentService.listarAtivosDoUsuario(user)
                    .stream()
                    .map(installment ->
                            new InstallmentResponseDTO(
                                    installment.getId(),
                                    installment.getDescription(),
                                    installment.getTotalAmount(),
                                    installment.getTotalParcels(),
                                    installment.getStartDate(),
                                    installment.getStatus().name(),
                                    installment.getCategory() != null
                                            ? installment.getCategory().getName()
                                            : null
                            )
                    )
                    .toList();

    return ResponseEntity.ok(response);
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> excluir(
        @PathVariable Long id,
        @AuthenticationPrincipal User user
) {
        installmentService.excluir(id, user);
        return ResponseEntity.noContent().build();
}


}
