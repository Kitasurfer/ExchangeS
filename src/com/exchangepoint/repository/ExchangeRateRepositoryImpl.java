package com.exchangepoint.repository;

import com.exchangepoint.model.Currency;
import com.exchangepoint.model.Rate;
import com.exchangepoint.model.Db;

import java.util.HashMap;
import java.util.Map;

public class ExchangeRateRepositoryImpl implements ExchangeRateRepository {
    private final Db db;

    public ExchangeRateRepositoryImpl(Db db) {
        this.db = db;
    }
    private final Map<String, Rate> rateMap = new HashMap<>();


    @Override
    public Rate getRate(String currencyPair) {
        return db.getRate(currencyPair);
    }

    @Override
    public void setRate(Currency fromCurrency, Currency toCurrency, double rate) {
        String currencyPair = fromCurrency.name() + "-" + toCurrency.name();
        rateMap.put(currencyPair, new Rate(rate));
    }
}
