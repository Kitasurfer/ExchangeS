package com.exchangepoint.service;

import com.exchangepoint.exception.AccountNotFoundException;
import com.exchangepoint.exception.CurrencyExchangeException;
import com.exchangepoint.exception.InsufficientFundsException;

public interface ExchangeService {
    void exchange(long fromAccountId, long toAccountId, double amount)
            throws AccountNotFoundException, CurrencyExchangeException, InsufficientFundsException;
}
