package com.exchangepoint.service;

import com.exchangepoint.exception.AccountNotFoundException;
import com.exchangepoint.exception.InsufficientFundsException;
import com.exchangepoint.model.Currency;

/**
 * Group: 52-1, "AIT Hi-tech team" GMBH
 * Author: Bogdan Fesenko
 * Date: 15-11-2024
 */
/*

 */
public interface AccountService {

    void openAccount(long userId, Currency currency);
    void closeAccount(long accountId) throws AccountNotFoundException;
    void deposit(long accountId, double amount) throws AccountNotFoundException;
    void withdraw(long accountId, double amount) throws AccountNotFoundException, InsufficientFundsException;

}
