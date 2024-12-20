package com.exchangepoint.service;

import com.exchangepoint.exception.InsufficientFundsException;
import com.exchangepoint.model.Account;
import com.exchangepoint.model.ExchangeRate;
import com.exchangepoint.model.User;
import com.exchangepoint.exception.ValidationException;

public interface UserService {
    void register(User user) throws ValidationException;

    void openAccount(User user);

    void topUpAccount(Account account, double amount );

    void withdrawFunds(Account account, double amount) throws InsufficientFundsException;

    void closeAccount(Account account);

    void exchangeAccount(ExchangeRate exchangeRate, double amount);




    User login(String email, String password) throws ValidationException;

    long getLastId();
}
