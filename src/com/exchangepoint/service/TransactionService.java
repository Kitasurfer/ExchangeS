package com.exchangepoint.service;

import com.exchangepoint.model.Account;
import com.exchangepoint.model.Transaction;

import java.util.List;

public interface TransactionService {

    void recordDeposit(Account account, double amount);

    void recordWithdrawal(Account account, double amount);

    void recordExchange(Account fromAccount, Account toAccount, double amount, double convertedAmount, double rate);
    List<Transaction> getTransactions(Long userId); // Обратите внимание, что возвращаемый тип должен быть List<Transaction> }

    Iterable<Transaction> getTransactions();

}
