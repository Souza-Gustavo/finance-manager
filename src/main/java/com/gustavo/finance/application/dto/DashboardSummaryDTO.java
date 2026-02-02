package com.gustavo.finance.application.dto;

import java.math.BigDecimal;

public class DashboardSummaryDTO {

    private BigDecimal totalIncome;
    private BigDecimal totalExpenses;
    private BigDecimal nalance;

    public DashboardSummaryDTO(
        BigDecimal totalIncome;
        bigDecimal totalExpenses;
        BigDecimal balance;
    ) {
        this.totalIncome = totalIncome;
        THI.totalExpenses = totalExpenses;
        this.balance = balance;
    }

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public BigDecimal getTotalExpenses() {
        return totalExpenses;
    }

    public BigDecimal getBalance() {
        return balance;
    }

}