package com.exchangepoint.service;

/**
 * Group: 52-1, "AIT Hi-tech team" GMBH
 * Author: Bogdan Fesenko
 * Date: 15-11-2024
 */
/*

 */


import com.exchangepoint.exception.InsufficientFundsException;
import com.exchangepoint.model.Account;
import com.exchangepoint.model.ExchangeRate;
import com.exchangepoint.model.User;
import com.exchangepoint.exception.ValidationException;

import java.util.Optional;

public interface UserService {
    void register(User user) throws ValidationException;

    void openAccount(User user);

    void topUpAccount(Account account, double amount );

    void withdrawFunds(Account account, double amount) throws InsufficientFundsException;

    void closeAccount(Account account);

    void exchangeAccount(ExchangeRate exchangeRate, double amount);

    void saveUser(User user);
    Optional<User> findById(long id);



    User login(String email, String password) throws ValidationException;

    long getLastId();
}
