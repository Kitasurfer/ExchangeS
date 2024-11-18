package com.exchangepoint.service;

/**
 * Group: 52-1, "AIT Hi-tech team" GMBH
 * Author: Bogdan Fesenko
 * Date: 15-11-2024
 */

import com.exchangepoint.model.Currency;
import com.exchangepoint.model.User;
import com.exchangepoint.repository.ExchangeRateRepository;
import com.exchangepoint.repository.UserRepository;
import com.exchangepoint.exception.UserNotFoundException;

import java.util.List;

public class AdminServiceImpl implements AdminService {

    private final ExchangeRateRepository exchangeRateRepository;
    private final UserRepository userRepository;

    public AdminServiceImpl(ExchangeRateRepository exchangeRateRepository, UserRepository userRepository) {
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

    public ExchangeRateRepository getExchangeRateRepository() {
        return exchangeRateRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
