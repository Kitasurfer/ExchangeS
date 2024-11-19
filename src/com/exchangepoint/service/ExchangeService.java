package com.exchangepoint.service;

import com.exchangepoint.exception.AccountException;
import com.exchangepoint.model.Currency;

public interface ExchangeService {
    double convertDirect(double amount, Currency from, Currency to) throws AccountException;
    double convertReverse(double amount, Currency to, Currency from) throws AccountException;
    void exchange(long fromAccountId, long toAccountId, double amount) throws AccountException;
    Currency getFromCurrency(long accountId) throws AccountException;
    Currency getToCurrency(long accountId) throws AccountException;
}
