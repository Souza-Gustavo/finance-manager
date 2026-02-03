package com.gustavo.finance.application.dto;

import java.math.BigDecimal;

public class MonthlySummaryDTO {

    private int year;
    private int month;

    private BigDecimal totalInstallments;

    public MonthlySummaryDTO(int year, int month, BigDecimal totalInstallments) {
        this.year = year;
        this.month = month;
        this.totalInstallments = totalInstallments;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public BigDecimal getTotalInstallments() {
        return totalInstallments;
    }

}

