package com.exchangepoint.repository;

import com.exchangepoint.model.Currency;
import com.exchangepoint.model.Db;

import java.util.HashMap;
import java.util.Map;

public class ExchangeRateRepositoryImpl implements ExchangeRateRepository {

    private Db db;
    private Map<String, Double> rates = new HashMap<>();

    public ExchangeRateRepositoryImpl(Db db) {
        this.db = db;
    }

    public ExchangeRateRepositoryImpl() {
        // Инициализация базовых курсов
        setRate(Currency.EUR, Currency.USD, 1.1);
        setRate(Currency.USD, Currency.EUR, 0.9);
        setRate(Currency.EUR, Currency.UAH, 30.0);
        setRate(Currency.UAH, Currency.EUR, 0.0333);
        // Добавьте остальные пары валют
    }

    @Override
    public double getRate(Currency from, Currency to) {
        String key = from.name() + "-" + to.name();
        return rates.getOrDefault(key, 0.0);
    }

    @Override
    public void setRate(Currency from, Currency to, double rate) {
        String key = from.name() + "-" + to.name();
        rates.put(key, rate);
    }
}
