package com.exchangepoint.service;

import com.exchangepoint.model.Account;
import com.exchangepoint.model.Currency;
import com.exchangepoint.exception.UserNotFoundException;
import com.exchangepoint.model.Transaction;
import com.exchangepoint.model.User;
import com.exchangepoint.repository.AccountRepository;
import com.exchangepoint.repository.ExchangeRateRepository;
import com.exchangepoint.repository.TransactionRepository;
import com.exchangepoint.repository.UserRepository;

import java.util.List;

public class AdminServiceImpl implements AdminService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final ExchangeRateRepository exchangeRateRepository;
    private final UserRepository userRepository;

    public AdminServiceImpl(TransactionRepository transactionRepository, AccountRepository accountRepository, ExchangeRateRepository exchangeRateRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.exchangeRateRepository = exchangeRateRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void setExchangeRate(Currency from, Currency to, double rate) {
        exchangeRateRepository.setRate(from, to, rate);
    }

    @Override
    public void blockUser(long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден."));
        user.setBlocked(true);
        userRepository.save(user);
    }

    @Override
    public void unblockUser(long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден."));
        user.setBlocked(false);
        userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Transaction> getUserTransactions(Long userId) {
        return transactionRepository.findByUserId(userId);
    }

    @Override
    public List<Account> getUserAccounts(Long userId) {
        return accountRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Счета пользователя не найдены"));
    }
}

