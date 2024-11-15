/*
package com.exchangepoint.service;

*/
/**
 * Group: 52-1, "AIT Hi-tech team" GMBH
 * Author: Bogdan Fesenko
 * Date: 15-11-2024
 *//*

*/
/*

 *//*


import com.exchangepoint.model.Account;
import com.exchangepoint.model.ExchangeRate;
import com.exchangepoint.model.User;
import com.exchangepoint.model.Role;
import com.exchangepoint.repository.UserRepository;
import com.exchangepoint.exception.ValidationException;
import com.exchangepoint.util.Validator;


public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Validator emailValidator;
    private final Validator validator;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.emailValidator = new Validator();
        this.validator = new Validator();
    }

    @Override
    public void register(User user) throws ValidationException {
        emailValidator.validateEmail(user.getEmail());
        validator.validatePassword(user.getPassword());
        user.setRole(Role.USER);
        userRepository.save(user);
    }

    @Override
    public void openAccount(User user) {

    }

    @Override
    public void topUpAccount(Account account, double amount) {

    }

    @Override
    public void withdrawFunds(Account account, double amount) {

    }

    @Override
    public void closeAccount(Account account) {

    }

    @Override
    public void exchangeAccount(ExchangeRate exchangeRate, double amount) {

    }

    @Override
    public User login(String email, String password) throws ValidationException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ValidationException("Пользователь не найден."));
        if (!user.getPassword().equals(password)) {
            throw new ValidationException("Неверный пароль.");
        }
        if (user.isBlocked()) {
            throw new ValidationException("Пользователь заблокирован.");
        }
        return user;
    }
}
*/
package com.exchangepoint.service;

import com.exchangepoint.exception.InsufficientFundsException;
import com.exchangepoint.model.Account;
import com.exchangepoint.model.ExchangeRate;
import com.exchangepoint.model.User;
import com.exchangepoint.model.Role;
import com.exchangepoint.repository.UserRepository;
import com.exchangepoint.exception.ValidationException;
import com.exchangepoint.util.Validator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Validator emailValidator;
    private final Validator validator;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.emailValidator = new Validator();
        this.validator = new Validator();
    }

    @Override
    public void register(User user) throws ValidationException {
        emailValidator.validateEmail(user.getEmail());
        validator.validatePassword(user.getPassword());
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(Role.USER);
        user.setRoles(userRoles);
        userRepository.save(user);
    }

    @Override
    public void openAccount(User user) {
        // Реализация открытия счета
    }

    @Override
    public void topUpAccount(Account account, double amount) {
        account.setBalance(account.getBalance() + amount);
        // Сохранение изменений в репозитории
    }

    @Override
    public void withdrawFunds(Account account, double amount) throws InsufficientFundsException {
        if (account.getBalance() < amount) {
            throw new InsufficientFundsException("Недостаточно средств.");
        }
        account.setBalance(account.getBalance() - amount);
        // Сохранение изменений в репозитории
    }

    @Override
    public void closeAccount(Account account) {
        // Реализация закрытия счета
    }

    @Override
    public void exchangeAccount(ExchangeRate exchangeRate, double amount) {
        // Реализация обмена валют
    }

    @Override
    public User login(String email, String password) throws ValidationException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ValidationException("Пользователь не найден."));
        if (!user.getPassword().equals(password)) {
            throw new ValidationException("Неверный пароль.");
        }
        if (user.isBlocked()) {
            throw new ValidationException("Пользователь заблокирован.");
        }
        return user;
    }

    @Override
    public long getLastId() {
        return userRepository.findAll()
                .stream()
                .mapToLong(User::getId)
                .max()
                .orElse(0);
    }
}