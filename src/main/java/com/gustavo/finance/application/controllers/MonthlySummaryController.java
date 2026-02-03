package com.gustavo.finance.application.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.gustavo.finance.application.dto.MonthlySummaryDTO;
import com.gustavo.finance.domain.entities.User;
import com.gustavo.finance.domain.services.MonthlySummaryService;


@RestController
@RequestMapping("/api/monthly-summary")
public class MonthlySummaryController {

    private final MonthlySummaryService monthlySummaryService;

    public MonthlySummaryController(MonthlySummaryService monthlySummaryService) {
        this.monthlySummaryService = monthlySummaryService;
    }

    @GetMapping
    public MonthlySummaryDTO getMonthlySummary(
        @RequestParam int year,
        @RequestParam int month,
        @AuthenticationPrincipal User user
    ) {
        return monthlySummaryService.gerarResumoMensal(user, year, month);
    }
}