package com.exchangepoint.service;

import com.exchangepoint.exception.AccountException;
import com.exchangepoint.exception.AccountNotFoundException;
import com.exchangepoint.exception.InsufficientFundsException;
import com.exchangepoint.model.Account;
import com.exchangepoint.model.Currency;
import com.exchangepoint.model.User;
import com.exchangepoint.repository.AccountRepository;

import java.util.List;
import java.util.Optional;

public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final TransactionService transactionService;

    // Конструктор
    public AccountServiceImpl(AccountRepository accountRepository, TransactionService transactionService) {
        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
    }

    @Override
    public void openAccount(Long userId, Currency currency) throws AccountException {
        // Создаем новый аккаунт
        Account account = new Account(currency, userId);

        // Проверяем, существует ли уже аккаунт с такой валютой
        List<Account> accounts = accountRepository.findByUserId(userId)
                .orElseThrow(() -> new AccountException("Не удалось получить список счетов пользователя."));
        accounts.stream()
                .filter(ac -> ac.getCurrency().equals(currency))
                .findFirst()
                .ifPresent(ac -> {
                    throw new AccountException("Аккаунт с такой валютой уже существует.");
                });

        // Сохраняем новый аккаунт
        accountRepository.save(account);
    }

    @Override
    public void closeAccount(long accountId) throws AccountNotFoundException {
        // Проверяем, существует ли аккаунт
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Счет не найден."));

        // Удаляем аккаунт
        accountRepository.delete(accountId);
    }

    @Override
    public void deposit(long accountId, double amount) throws AccountNotFoundException, AccountException {
        if (amount <= 0) {
            throw new AccountException("Сумма пополнения должна быть положительной.");
        }

        // Получаем аккаунт
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Счет не найден."));

        // Пополняем баланс
        account.setBalance(account.getBalance() + amount);

        // Сохраняем изменения
        accountRepository.save(account);

        // Регистрируем транзакцию
        transactionService.recordDeposit(account, amount);
    }

    @Override
    public void withdraw(long accountId, double amount) throws AccountNotFoundException, InsufficientFundsException {
        if (amount <= 0) {
            throw new AccountException("Сумма снятия должна быть положительной.");
        }

        // Получаем аккаунт
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Счет не найден."));

        // Проверяем баланс
        if (account.getBalance() < amount) {
            throw new InsufficientFundsException("Недостаточно средств для снятия.");
        }

        // Снимаем средства
        account.setBalance(account.getBalance() - amount);

        // Сохраняем изменения
        accountRepository.save(account);

        // Регистрируем транзакцию
        transactionService.recordWithdrawal(account, amount);
    }

    @Override
    public Account getAccountByUserId(long userId, Currency currency) throws AccountException {
        // Получаем список аккаунтов пользователя
        List<Account> accounts = accountRepository.findByUserId(userId)
                .orElseThrow(() -> new AccountException("Не удалось получить список счетов пользователя."));

        // Ищем аккаунт с указанной валютой
        return accounts.stream()
                .filter(account -> account.getCurrency().equals(currency))
                .findFirst()
                .orElseThrow(() -> new AccountException("Счет с указанной валютой не найден."));
    }
}
