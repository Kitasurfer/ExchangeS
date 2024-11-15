package com.exchangepoint.repository;

/**
 * Group: 52-1, "AIT Hi-tech team" GMBH
 * Author: Bogdan Fesenko
 * Date: 15-11-2024
 */
/*

 */

import com.exchangepoint.model.Account;

import java.util.*;

public class AccountRepositoryImpl implements AccountRepository {

    private Map<Long, Account> accountsById = new HashMap<>();
    private Map<Long, List<Account>> userAccounts = new HashMap<>();
    private long accountIdSequence = 1;

    @Override
    public void save(Account account) {
        if (account.getId() == 0) {
            account.setId(accountIdSequence++);
        }
        accountsById.put(account.getId(), account);
        userAccounts.computeIfAbsent(account.getUserId(), k -> new ArrayList<>()).add(account);
    }

    @Override
    public Optional<Account> findById(long id) {
        return Optional.ofNullable(accountsById.get(id));
    }

    @Override
    public List<Account> findByUserId(long userId) {
        return userAccounts.getOrDefault(userId, new ArrayList<>());
    }

    @Override
    public void delete(long id) {
        Account account = accountsById.remove(id);
        if (account != null) {
            List<Account> accounts = userAccounts.get(account.getUserId());
            if (accounts != null) {
                accounts.remove(account);
            }
        }
    }
}
