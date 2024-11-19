package com.exchangepoint.repository;

import com.exchangepoint.model.Db;
import com.exchangepoint.model.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TransactionRepositoryImpl implements TransactionRepository {

    private final Db db;

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
        // Возвращаем список транзакций пользователя или пустой список, если транзакций нет
        return db.getUserTransactions()
                .getOrDefault(userId, new ArrayList<>());
    }

    @Override
    public List<Transaction> findAll() {
        // Составляем единый список всех транзакций для всех пользователей
        List<Transaction> allTransactions = new ArrayList<>();
        for (List<Transaction> userTransactionList : db.getUserTransactions().values()) {
            allTransactions.addAll(userTransactionList);
        }
        return allTransactions;
    }
}
