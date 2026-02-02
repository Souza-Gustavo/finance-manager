package com.gustavo.finance.application.controllers;

import com.gustavo.finance.application.dto.DashboardSummaryDTO;
import com.gustavo.finance.domain.entities.User;
import com.gustavo.finance.domain.services.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RrestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/summary")
    public ResponseEntity<DashboardSummaryDTO> summary(
        @RequestParam int month,
        @RequestParam int year,
        @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(
            dashboardService.gerarResumoMensal(usaer, month, year)
        );
    
    }

}