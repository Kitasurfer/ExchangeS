package com.exchangepoint.service;

import com.exchangepoint.exception.AccountException;
import com.exchangepoint.exception.AccountNotFoundException;
import com.exchangepoint.exception.InsufficientFundsException;
import com.exchangepoint.exception.UserNotFoundException;
import com.exchangepoint.model.Account;
import com.exchangepoint.model.Currency;
import com.exchangepoint.model.Transaction;
import com.exchangepoint.model.User;

import java.util.List;

public interface AdminService {
    void setExchangeRate(Currency from, Currency to, double rate);
    void blockUser(long userId) throws UserNotFoundException;
    void unblockUser(long userId) throws UserNotFoundException;
    List<User> getUsers();
    List<Transaction> getUserTransactions(Long userId);
    List<Account> getUserAccounts(Long userId);
    void addUser(User user);
    User findUserById(Long userId) throws UserNotFoundException;
    void deleteUserById(Long userId) throws UserNotFoundException;
    void updateUser(User user) throws UserNotFoundException;
}
