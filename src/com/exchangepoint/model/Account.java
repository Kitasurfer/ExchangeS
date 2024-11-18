package com.exchangepoint.model;



public class Account {
    private Long id;             // Уникальный идентификатор счета
    private Currency currency;   // Валюта счета
    private double balance;      // Баланс счета
    private Long userId;         // ID пользователя, владельца счета

    public Account() {
    }

    public Account(Long id, Currency currency, double balance, Long userId) {
        this.id = id;
        this.currency = currency;
        this.balance = balance;
        this.userId = userId;
    }

    public Account(Currency currency, Long userId) {
        this.currency = currency;
        this.balance = 0;
        this.userId = userId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Currency getCurrency() { return currency; }
    public void setCurrency(Currency currency) { this.currency = currency; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", currency=" + currency +
                ", balance=" + balance +
                ", userId=" + userId +
                '}';
    }
}
