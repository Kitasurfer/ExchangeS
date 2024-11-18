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
        account.setId(accountId);
        accountId++;
        accounts.add(account);
        return accounts;
    }

    private Map<Long, User> initUsers() {
        Map<Long, User> map = new HashMap<>();
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ADMIN);
        User user = new User(
                userId++, "admin1", "admin1@example.com",
                "adminpassword1", roles,
                new ArrayList<>(), false);
        map.put(user.getId(), user);

        // Создаем тестового пользователя testuser@example.com  testpassword
        Set<Role> testRoles = new HashSet<>();
        testRoles.add(Role.USER); // Предполагается, что есть роль USER
        User testUser = new User(
                userId++, "testuser", "testuser@example.com",
                "testpassword", testRoles,
                new ArrayList<>(), false);
        map.put(testUser.getId(), testUser);
        return map;
    }

    private Map<String, Rate> initRateMap() {
        Map<String, Rate> map = new HashMap<>();

        // Курсы валют актуальны на определенную дату
        // Обязательно обновите курсы до актуальных перед использованием

        // Кросс-курсы между фиатными валютами
        map.put("EUR-USD", new Rate(1.10));
        map.put("EUR-UAH", new Rate(40.00));
        map.put("USD-EUR", new Rate(0.91));
        map.put("USD-UAH", new Rate(36.50));
        map.put("UAH-EUR", new Rate(0.025));
        map.put("UAH-USD", new Rate(0.0274));

        // Курсы между фиатными валютами и криптовалютами
        map.put("EUR-BTC", new Rate(0.000025));
        map.put("BTC-EUR", new Rate(40000.0));
        map.put("EUR-ETH", new Rate(0.00035));
        map.put("ETH-EUR", new Rate(2850.0));

        map.put("USD-BTC", new Rate(0.000023));
        map.put("BTC-USD", new Rate(43000.0));
        map.put("USD-ETH", new Rate(0.00032));
        map.put("ETH-USD", new Rate(3125.0));

        map.put("UAH-BTC", new Rate(0.0000006));
        map.put("BTC-UAH", new Rate(1600000.0));
        map.put("UAH-ETH", new Rate(0.000008));
        map.put("ETH-UAH", new Rate(125000.0));

        // Курсы между криптовалютами
        map.put("BTC-ETH", new Rate(14.0));
        map.put("ETH-BTC", new Rate(0.0714));

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
}
