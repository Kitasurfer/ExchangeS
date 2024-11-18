package com.exchangepoint.model;


/*

 */


public class Account {
    private long id;             // Уникальный идентификатор счета
    private Currency currency;   // Валюта счета
    private double balance;      // Баланс счета
    private long userId;         // ID пользователя, владельца счета

    public Account() {
    }

    public Account(long id, Currency currency, double balance, long userId) {
        this.id = id;
        this.currency = currency;
        this.balance = balance;
        this.userId = userId;
    }


    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public Currency getCurrency() { return currency; }
    public void setCurrency(Currency currency) { this.currency = currency; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public long getUserId() { return userId; }
    public void setUserId(long userId) { this.userId = userId; }
}
