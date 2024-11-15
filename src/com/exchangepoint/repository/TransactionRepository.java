package com.exchangepoint.repository;

/**
 * Group: 52-1, "AIT Hi-tech team" GMBH
 * Author: Bogdan Fesenko
 * Date: 15-11-2024
 */
/*

 */


import com.exchangepoint.model.Transaction;
import java.util.List;

public interface TransactionRepository {
    void save(Transaction transaction);
    List<Transaction> findByUserId(long userId);
}
