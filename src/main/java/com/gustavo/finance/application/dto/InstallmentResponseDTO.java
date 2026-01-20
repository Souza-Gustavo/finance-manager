package com.gustavo.finance.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InstallmentResponseDTO {

    private Long id;
    private String description;
    private BigDecimal totalAmount;
    private Integer totalParcels;
    private LocalDate startDate;
    private String status;
    private String categoryName;

    public InstallmentResponseDTO(
            Long id,
            String description,
            BigDecimal totalAmount,
            Integer totalParcels,
            LocalDate startDate,
            String status,
            String categoryName
    ) {
        this.id = id;
        this.description = description;
        this.totalAmount = totalAmount;
        this.totalParcels = totalParcels;
        this.startDate = startDate;
        this.status = status;
        this.categoryName = categoryName;
    }

    public Long getId() { return id; }
    public String getDescription() { return description; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public Integer getTotalParcels() { return totalParcels; }
    public LocalDate getStartDate() { return startDate; }
    public String getStatus() { return status; }
    public String getCategoryName() { return categoryName; }
}

