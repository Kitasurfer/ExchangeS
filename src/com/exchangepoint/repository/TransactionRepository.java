package com.exchangepoint.repository;

import com.exchangepoint.model.Transaction;
import java.util.List;

public interface TransactionRepository {
    void save(Transaction transaction);
    List<Transaction> findByUserId(long userId);

    Iterable<Transaction> findAll();
}
