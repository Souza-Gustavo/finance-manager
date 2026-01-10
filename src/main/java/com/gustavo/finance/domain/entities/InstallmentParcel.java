package com.gustavo.finance.domain.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "installment_parcels")
public class InstallmentParcel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parcel_number", nullable = false)
    private Integer parcelNumber;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(nullable = false)
    private boolean paid = false;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @ManyToOne
    @JoinColumn(name = "installment_id", nullable = false)
    private Installment installment;

    @OneToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

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

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Installment getInstallment() {
        return installment;
    }

    public void setInstallment(Installment installment) {
        this.installment = installment;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    

}
