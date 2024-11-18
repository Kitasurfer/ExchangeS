package com.exchangepoint.model;

/**
 * Group: 52-1, "AIT Hi-tech team" GMBH
 * Author: Bogdan Fesenko
 * Date: 15-11-2024
 */
/*

 */


public class ExchangeRate {
    private Currency currencyFrom;   // Валюта, из которой производится обмен
    private Currency currencyTo;     // Валюта, в которую производится обмен
    private double rate;             // Курс обмена

    public ExchangeRate() {
    }

    public ExchangeRate(Currency currencyFrom, Currency currencyTo, double rate) {
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.rate = rate;
    }


    public Currency getCurrencyFrom() { return currencyFrom; }
    public void setCurrencyFrom(Currency currencyFrom) { this.currencyFrom = currencyFrom; }

    public Currency getCurrencyTo() { return currencyTo; }
    public void setCurrencyTo(Currency currencyTo) { this.currencyTo = currencyTo; }

    public double getRate() { return rate; }
    public void setRate(double rate) { this.rate = rate; }
}
