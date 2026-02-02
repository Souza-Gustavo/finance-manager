package com.gustavo.finance.domain.services;

import com.gustavo.finance.application.dto.DashboardSummaryDTO;
import com.gustavo.finance.domain.entities.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DashboardService {

    public DashboardSummaryDTO gerarResumoMensal(
        User user,
        int month,
        int year
    ) {

        BigDecimal totalIncome = BigDecimal.ZERO;
        BigDecimal totalExpenses = BigDecimal.ZERO;
        BigDecimal balance = BigDecimal.ZERO;

        return new DashboardSummaryDTO(
            totalIncome,
            totalExpenses,
            balance
        );
    }

}