package com.exchangepoint.repository;

import com.exchangepoint.model.Currency;
import com.exchangepoint.model.Db;
import com.exchangepoint.model.Rate;

import java.util.HashMap;
import java.util.Map;

public class ExchangeRateRepositoryImpl implements ExchangeRateRepository {

    private final Map<String, Rate> rates = new HashMap<>();

    public ExchangeRateRepositoryImpl(Db db) {
        // Инициализация базовых курсов
        setRate(Currency.USD, Currency.EUR, 0.93);
        setRate(Currency.EUR, Currency.USD, 1.08);
        setRate(Currency.EUR, Currency.UAH, 30.0);
        setRate(Currency.UAH, Currency.EUR, 0.0333);
        // Добавьте дополнительные пары валют при необходимости
    }

    @Override
    public Rate getRate(Currency from, Currency to) {
        String key = from.name() + "-" + to.name();
        return rates.get(key);
    }

    @Override
    public void setRate(Currency from, Currency to, double rateValue) {
        String key = from.name() + "-" + to.name();
        rates.put(key, new Rate(rateValue)); // Создаем объект Rate и сохраняем
    }
}