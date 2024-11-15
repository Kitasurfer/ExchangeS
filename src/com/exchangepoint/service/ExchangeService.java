package com.exchangepoint.service;

/**
 * Group: 52-1, "AIT Hi-tech team" GMBH
 * Author: Bogdan Fesenko
 * Date: 15-11-2024
 */
/*

 */


import com.exchangepoint.exception.AccountNotFoundException;
import com.exchangepoint.exception.CurrencyExchangeException;
import com.exchangepoint.exception.InsufficientFundsException;

public interface ExchangeService {
    void exchange(long fromAccountId, long toAccountId, double amount)
            throws AccountNotFoundException, CurrencyExchangeException, InsufficientFundsException;
}
