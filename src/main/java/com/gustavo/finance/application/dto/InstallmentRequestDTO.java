package com.gustavo.finance.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InstallmentRequestDTO {

    private String description;
    private BigDecimal totalAmount;
    private Integer totalParcels;
    private LocalDate startDate;
    private Long userId;


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
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
}
