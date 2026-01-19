package com.gustavo.finance.application.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InstallmentRequestDTO {

    @NotBlank
    private String description;

    @NotNull
    @Positive
    private BigDecimal totalAmount;

    @NotNull
    @Positive
    private Integer totalParcels;

    @NotNull
    private LocalDate startDate;


    private Long categoryId;

    // getters e setters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getTotalParcels() {
        return totalParcels;
    }

    public void setTotalParcels(Integer totalParcels) {
        this.totalParcels = totalParcels;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}

