package com.exchangepoint.repository;

import com.exchangepoint.model.Account;
import com.exchangepoint.model.Db;

import java.util.*;

public class AccountRepositoryImpl implements AccountRepository {

    private final Db db;

    public AccountRepositoryImpl(Db db) {
        this.db = db;
    }

    @Override
    public void save(Account account) {
        if (account.getId() == null) {
            Long accountId = db.getAccountId();
            account.setId(accountId);
            db.getAccounts().add(account);
        } else {
            db.getAccounts().stream()
                    .filter(ac -> ac.getId().equals(account.getId()))
                    .findFirst().ifPresent(ac -> {
                        db.getAccounts().remove(ac);
                        db.getAccounts().add(account);
                    });
        }
    }

    @Override
    public Optional<Account> findById(long id) {
        return db.getAccounts().stream().filter(ac -> ac.getId().equals(id)).findFirst();
    }

    @Override
    public Optional<List<Account>> findByUserId(Long userId) {
        return Optional.of(db.getAccounts().stream()
                .filter(ac -> ac.getUserId().equals(userId))
                .toList());
    }

    @Override
    public void delete(Long id) {
        db.getAccounts().removeIf(ac -> ac.getId().equals(id));
    }
}
