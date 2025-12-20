package com.gustavo.domain.services;

import com.gustavo.domain.entities.Transaction;
import com.gustavo.domain.entities.User;
import com.gustavo.domain.enums.TransactionType;
import com.gustavo.domain.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

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
