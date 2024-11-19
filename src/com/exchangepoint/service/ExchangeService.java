package com.exchangepoint.service;

import com.exchangepoint.exception.CurrencyExchangeException;
import com.exchangepoint.model.Currency;

public interface ExchangeService {
    double convertDirect(double amount, Currency from, Currency to) throws CurrencyExchangeException;
    double convertReverse(double amount, Currency to, Currency from) throws CurrencyExchangeException;
}
