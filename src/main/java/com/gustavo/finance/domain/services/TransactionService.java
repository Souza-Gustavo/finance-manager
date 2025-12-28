package com.gustavo.finance.domain.services;

import org.springframework.stereotype.Service;

import com.gustavo.finance.domain.entities.Transaction;
import com.gustavo.finance.domain.entities.User;
import com.gustavo.finance.domain.enums.TransactionType;
import com.gustavo.finance.domain.repositories.TransactionRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<Transaction> findByMonth(User user, int year, int month) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        return transactionRepository.findByUserAndDateBetween(user, start, end);
    }

    public List<Transaction> findByTypeAndMonth(
            User user,
            TransactionType type,
            int year,
            int month
    ) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        return transactionRepository.findByUserAndTypeAndDateBetween(
                user, type, start, end
        );
    }
}
