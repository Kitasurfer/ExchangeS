package com.exchangepoint.repository;


import com.exchangepoint.model.Transaction;
import java.util.*;

public class TransactionRepositoryImpl implements TransactionRepository {

    private Map<Long, List<Transaction>> userTransactions = new HashMap<>();
    private long transactionIdSequence = 1;

    @Override
    public void save(Transaction transaction) {
        if (transaction.getId() == 0) {
            transaction.setId(transactionIdSequence++);
        }
        userTransactions.computeIfAbsent(transaction.getUserId(), k -> new ArrayList<>()).add(transaction);
    }

    @Override
    public List<Transaction> findByUserId(long userId) {
        return userTransactions.getOrDefault(userId, new ArrayList<>());
    }
}
