package com.gustavo.finance.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InstallmentParcelResponseDTO {

    private Long id;
    private Integer parcelNumber;
    private BigDecimal amount;
    private LocalDate dueDate;
    private Boolean paid;
    private LocalDate paymentDate;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getParcelNumber() {
        return parcelNumber;
    }
    public void setParcelNumber(Integer parcelNumber) {
        this.parcelNumber = parcelNumber;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
    public Boolean getPaid() {
        return paid;
    }
    public void setPaid(Boolean paid) {
        this.paid = paid;
    }
    public LocalDate getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    

}