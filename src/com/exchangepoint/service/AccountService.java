package com.exchangepoint.service;

import com.exchangepoint.exception.AccountException;
import com.exchangepoint.exception.AccountNotFoundException;
import com.exchangepoint.exception.InsufficientFundsException;
import com.exchangepoint.model.Account;
import com.exchangepoint.model.Currency;
import com.exchangepoint.model.User;

public interface AccountService {
    void openAccount(Long userId, Currency currency) throws AccountException;
    void closeAccount(long accountId) throws AccountNotFoundException;
    void deposit(User user, double amount, String currencyStr) throws AccountNotFoundException, AccountException;
    void withdraw(long accountId, double amount) throws AccountNotFoundException, InsufficientFundsException;
    Account getAccountByUserId(long userId, Currency currency) throws AccountException;
    Account getLatestAccountByUserId(long userId) throws AccountException;
    Account getLatestAccountByUserId(long userId, Currency currency) throws AccountException;
}
