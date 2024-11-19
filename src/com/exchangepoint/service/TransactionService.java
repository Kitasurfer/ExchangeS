package com.exchangepoint.service;

import com.exchangepoint.model.Account;
import com.exchangepoint.model.Transaction;

public interface TransactionService {

    void recordDeposit(Account account, double amount);

    void recordWithdrawal(Account account, double amount);

    void recordExchange(Account fromAccount, Account toAccount, double amount, double convertedAmount, double rate);

    Iterable<Transaction> getTransactions();
}
