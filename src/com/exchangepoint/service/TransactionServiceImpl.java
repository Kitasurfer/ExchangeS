package com.exchangepoint.service;

import com.exchangepoint.model.Account;
import com.exchangepoint.model.Currency;
import com.exchangepoint.model.Transaction;
import com.exchangepoint.repository.TransactionRepository;

import java.time.LocalDateTime;

public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void recordDeposit(Account account, double amount) {
        Transaction transaction = new Transaction(
                LocalDateTime.now(),
                "Пополнение",
                account.getCurrency(),
                amount,
                account.getBalance(),
                account.getUserId()
        );
        transactionRepository.save(transaction);
    }

    @Override
    public void recordWithdrawal(Account account, double amount) {
        Transaction transaction = new Transaction(
                LocalDateTime.now(),
                "Снятие",
                account.getCurrency(),
                amount,
                account.getBalance(),
                account.getUserId()
        );
        transactionRepository.save(transaction);
    }

    @Override
    public void recordExchange(Account fromAccount, Account toAccount, double amount, double convertedAmount, double rate) {
        // Запись транзакции для счета списания
        Transaction fromTransaction = new Transaction(
                LocalDateTime.now(),
                "Обмен - Списание",
                fromAccount.getCurrency(),
                amount,
                fromAccount.getBalance(),
                fromAccount.getUserId()
        );
        transactionRepository.save(fromTransaction);

        // Запись транзакции для счета зачисления
        Transaction toTransaction = new Transaction(
                LocalDateTime.now(),
                "Обмен - Зачисление",
                toAccount.getCurrency(),
                convertedAmount,
                toAccount.getBalance(),
                toAccount.getUserId()
        );
        transactionRepository.save(toTransaction);
    }

    @Override
    public Iterable<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }
}

