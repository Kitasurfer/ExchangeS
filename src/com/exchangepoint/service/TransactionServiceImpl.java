package com.exchangepoint.service;

import com.exchangepoint.model.Account;
import com.exchangepoint.repository.TransactionRepository;

public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void recordDeposit(Account account, double amount) {

    }

    @Override
    public void recordWithdrawal(Account account, double amount) {

    }

    @Override
    public void recordExchange(Account fromAccount, Account toAccount, double amount, double convertedAmount, double rate) {

    }
}
