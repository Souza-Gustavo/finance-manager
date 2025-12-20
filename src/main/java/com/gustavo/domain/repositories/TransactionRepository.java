package com.gustavo.domain.repositories;

import com.gustavo.domain.entities.Transaction;
import com.gustavo.domain.entities.User;
import com.gustavo.domain.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

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
