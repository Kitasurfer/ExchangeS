package com.exchangepoint.model;

import java.util.*;

public class Db {
    private Map<String, Rate> rateMap;
    private Map<Long, User> users;
    private Map<Long, List<Transaction>> userTransactions;
    private List<Account> accounts;
    private Long userId = 1L;
    private Long transactionId = 1L;
    private Long accountId = 1L;

    public Db() {
        this.rateMap = initRateMap();
        this.users = initUsers();
        this.accounts = initAccounts();
        this.userTransactions = new HashMap<>();
    }

    private List<Account> initAccounts() {
        List<Account> accounts = new ArrayList<>();
        Account account = new Account(Currency.EUR, 2L);
        account.setId(accountId++);
        accounts.add(account);
        return accounts;
    }

    private Map<Long, User> initUsers() {
        Map<Long, User> map = new HashMap<>();
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ADMIN);
        User user = new User(userId++, "admin1", "1", "1", roles, new ArrayList<>(), false);
        map.put(user.getId(), user);

        Set<Role> testRoles = new HashSet<>();
        testRoles.add(Role.USER);
        User testUser = new User(userId++, "testuser", "2", "2", testRoles, new ArrayList<>(), false);
        map.put(testUser.getId(), testUser);
        return map;
    }

    private Map<String, Rate> initRateMap() {
        Map<String, Rate> map = new HashMap<>();
        map.put("EUR-USD", new Rate(1.10));
        map.put("EUR-UAH", new Rate(40.00));
        map.put("EUR-BTC", new Rate(0.000025));
        map.put("EUR-ETH", new Rate(0.00035));
        map.put("USD-EUR", new Rate(1 / 1.10));
        map.put("UAH-EUR", new Rate(1 / 40.00));
        map.put("BTC-EUR", new Rate(1 / 0.000025));
        map.put("ETH-EUR", new Rate(1 / 0.00035));
        map.put("BTC-ETH", new Rate(14.0));
        map.put("ETH-BTC", new Rate(1 / 14.0));
        map.put("EUR-GBP", new Rate(0.85));
        map.put("GBP-EUR", new Rate(1 / 0.85));
        map.put("EUR-CHF", new Rate(1.08));
        map.put("CHF-EUR", new Rate(1 / 1.08));
        map.put("EUR-NOK", new Rate(10.00));
        map.put("NOK-EUR", new Rate(1 / 10.00));
        map.put("EUR-SEK", new Rate(10.22));
        map.put("SEK-EUR", new Rate(1 / 10.22));
        map.put("EUR-DKK", new Rate(7.44));
        map.put("DKK-EUR", new Rate(1 / 7.44));

        return map;
    }


    // Дополнительные методы для доступа к rateMap
    public Rate getRate(String currencyPair) {
        return rateMap.get(currencyPair);
    }

    public void setRate(String currencyPair, Rate rate) {
        rateMap.put(currencyPair, rate);
    }

    public Map<String, Rate> getAllRates() {
        return rateMap;
    }

    public Map<String, Rate> getRateMap() {
        return rateMap;
    }

    public void setRateMap(Map<String, Rate> rateMap) {
        this.rateMap = rateMap;
    }

    public Map<Long, User> getUsers() {
        return users;
    }

    public void setUsers(Map<Long, User> users) {
        this.users = users;
    }

    public Map<Long, List<Transaction>> getUserTransactions() {
        return userTransactions;
    }

    public void setUserTransactions(Map<Long, List<Transaction>> userTransactions) {
        this.userTransactions = userTransactions;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public List<Transaction> getAllTransactions() {
        return null;
    }


}


