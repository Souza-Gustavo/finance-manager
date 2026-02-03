package com.gustavo.finance.application.dto;

import java.math.BigDecimal;

public class CategoryMonthlySummaryDTO {

    private String category;
    private BigDecimal total;


    public CategoryMonthlySummaryDTO(String category, BigDecimal total) {
        this.category = category;
        this.total = total;
    }

    public String getCategory() {
        return category;
    }

    public BigDecimal getTotal() {
        return total;
    }
}