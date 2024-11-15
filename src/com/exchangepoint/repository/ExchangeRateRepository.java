package com.exchangepoint.repository;

import com.exchangepoint.model.Currency;

/**
 * Group: 52-1, "AIT Hi-tech team" GMBH
 * Author: Bogdan Fesenko
 * Date: 15-11-2024
 */
/*

 */


public interface ExchangeRateRepository {
    double getRate(Currency from, Currency to);
    void setRate(Currency from, Currency to, double rate);
}
