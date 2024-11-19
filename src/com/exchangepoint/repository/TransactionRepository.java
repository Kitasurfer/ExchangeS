package com.exchangepoint.repository;

import com.exchangepoint.model.Transaction;
import java.util.List;

public interface TransactionRepository {
    void save(Transaction transaction);
    List<Transaction> findByUserId(long userId);
    List<Transaction> findByAccountId(long userId);
    List<Transaction> findAll();





}
