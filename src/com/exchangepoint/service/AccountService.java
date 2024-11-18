package com.exchangepoint.service;

import com.exchangepoint.exception.AccountNotFoundException;
import com.exchangepoint.exception.InsufficientFundsException;
import com.exchangepoint.model.Account;
import com.exchangepoint.model.Currency;
import com.exchangepoint.model.User;

import java.util.Optional;

public interface AccountService {

    void openAccount(Long userId, Currency currency);
    void closeAccount(long accountId) throws AccountNotFoundException;
    void deposit(User user, double amount, String currencyStr) throws AccountNotFoundException;
    void withdraw(long accountId, double amount) throws AccountNotFoundException, InsufficientFundsException;
    Account getAccountByUserId(long userId, Currency currency);
}
