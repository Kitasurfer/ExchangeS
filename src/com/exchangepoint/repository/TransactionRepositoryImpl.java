package com.exchangepoint.repository;

/**
 * Group: 52-1, "AIT Hi-tech team" GMBH
 * Author: Bogdan Fesenko
 * Date: 15-11-2024
 */
/*

 */



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
