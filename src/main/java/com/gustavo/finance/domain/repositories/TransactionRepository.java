package com.gustavo.finance.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustavo.finance.domain.entities.Transaction;
import com.gustavo.finance.domain.entities.User;
import com.gustavo.finance.domain.enums.TransactionType;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

    List<Transaction> findByUserAndDateBetween(
        User user,
        LocalDate startDate,
        LocalDate endDate
    );

    List<Transaction> findByUserAndTypeAndDateBetween(
        User user,
        TransactionType type,
        LocalDate startDate,
        LocalDate endDate
    );
    
}
