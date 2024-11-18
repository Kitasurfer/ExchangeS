package com.exchangepoint.repository;

import com.exchangepoint.model.Currency;


public interface ExchangeRateRepository {
    double getRate(Currency from, Currency to);
    void setRate(Currency from, Currency to, double rate);
}
