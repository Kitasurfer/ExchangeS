package com.exchangepoint.repository;

import com.exchangepoint.model.Currency;
import com.exchangepoint.model.Rate;


public interface ExchangeRateRepository {
    Rate getRate(String currencyPair);
    void setRate(Currency fromCurrency, Currency toCurrency, double rate);
}

