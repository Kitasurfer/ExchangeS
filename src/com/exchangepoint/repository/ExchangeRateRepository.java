package com.exchangepoint.repository;

import com.exchangepoint.model.Currency;
import com.exchangepoint.model.Rate;

public interface ExchangeRateRepository {
    Rate getRate(Currency from, Currency to); // Вернуть объект Rate
    void setRate(Currency from, Currency to, double rate); // Установить курс
}

