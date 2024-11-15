package com.exchangepoint.repository;

/**
 * Group: 52-1, "AIT Hi-tech team" GMBH
 * Author: Bogdan Fesenko
 * Date: 15-11-2024
 */
/*

 */



import com.exchangepoint.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    void save(Account account);
    Optional<Account> findById(long id);
    List<Account> findByUserId(long userId);
    void delete(long id);
}
