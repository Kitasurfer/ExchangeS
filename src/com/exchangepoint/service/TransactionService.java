package com.exchangepoint.service;

import com.exchangepoint.model.Account;

/**
 * Group: 52-1, "AIT Hi-tech team" GMBH
 * Author: Bogdan Fesenko
 * Date: 15-11-2024
 */
/*

 */

public interface TransactionService {


    void recordDeposit(Account account, double amount);

    void recordWithdrawal(Account account, double amount);

    void recordExchange(Account fromAccount, Account toAccount, double amount, double convertedAmount, double rate);
}
