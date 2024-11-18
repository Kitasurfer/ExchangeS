package com.exchangepoint.repository;

/**
 * Group: 52-1, "AIT Hi-tech team" GMBH
 * Author: Bogdan Fesenko
 * Date: 15-11-2024
 */
import com.exchangepoint.model.Db;

import com.exchangepoint.model.Transaction;
import java.util.*;

public class TransactionRepositoryImpl implements TransactionRepository {

    private Db db;

    public TransactionRepositoryImpl(Db db) {
        this.db = db;
    }

    @Override
    public void save(Transaction transaction) {
        // Получение нового ID транзакции и установка его для новой транзакции
        long id = db.getTransactionId();
        transaction.setId(++id);

        // Получаем текущие транзакции пользователя
        Map<Long, List<Transaction>> userTransactions = db.getUserTransactions();

        // Добавляем новую транзакцию к пользователю
        userTransactions
                .computeIfAbsent(transaction.getUserId(), k -> new ArrayList<>())
                .add(transaction);

        // Сохраняем обновленный список транзакций в базе данных
        db.setUserTransactions(userTransactions);

        // Обновляем счетчик ID транзакций
        db.setTransactionId(id);
    }

    @Override
    public List<Transaction> findByUserId(long userId) {
        return db.getUserTransactions()
                .getOrDefault(userId, new ArrayList<>());
    }
}
