package com.exchangepoint.model;

/**
 * Group: 52-1, "AIT Hi-tech team" GMBH
 * Author: Bogdan Fesenko
 * Date: 15-11-2024
 */
/*

 */


import java.time.LocalDateTime;

public class Transaction {
    private long id;                   // Уникальный идентификатор операции
    private LocalDateTime timestamp;   // Дата и время операции
    private String type;               // Тип операции ("Пополнение", "Снятие", "Обмен")
    private Currency currency;         // Валюта операции
    private double amount;             // Сумма операции
    private double balanceAfter;       // Баланс на счете после операции
    private long userId;               // ID пользователя

    public Transaction() {
    }

    public Transaction(long id, LocalDateTime timestamp, String type, Currency currency, double amount, double balanceAfter, long userId) {
        this.id = id;
        this.timestamp = timestamp;
        this.type = type;
        this.currency = currency;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.userId = userId;
    }

    // Геттеры и сеттеры
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Currency getCurrency() { return currency; }
    public void setCurrency(Currency currency) { this.currency = currency; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public double getBalanceAfter() { return balanceAfter; }
    public void setBalanceAfter(double balanceAfter) { this.balanceAfter = balanceAfter; }

    public long getUserId() { return userId; }
    public void setUserId(long userId) { this.userId = userId; }
}
