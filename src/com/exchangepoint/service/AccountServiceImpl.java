package com.exchangepoint.service;

import com.exchangepoint.exception.AccountException;
import com.exchangepoint.exception.AccountNotFoundException;
import com.exchangepoint.exception.InsufficientFundsException;
import com.exchangepoint.model.Account;
import com.exchangepoint.model.Currency;
import com.exchangepoint.model.User;
import com.exchangepoint.repository.AccountRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final TransactionService transactionService;

    public AccountServiceImpl(AccountRepository accountRepository, TransactionService transactionService) {
        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
    }

    @Override
    public void openAccount(Long userId, Currency currency) throws AccountException {
        Account account = new Account(currency, userId);

        List<Account> accounts = accountRepository.findByUserId(userId)
                .orElseThrow(() -> new AccountException("Не удалось получить список счетов пользователя."));
        accounts.stream()
                .filter(ac -> ac.getCurrency().equals(currency))
                .findFirst()
                .ifPresent(ac -> {
                    throw new AccountException("Аккаунт с такой валютой уже существует.");
                });

        accountRepository.save(account);
    }

    @Override
    public void closeAccount(long accountId) throws AccountNotFoundException {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Счет не найден."));
        accountRepository.delete(accountId);
    }

    @Override
    public void deposit(User user, double amount, String currencyStr) throws AccountNotFoundException, AccountException {
        if (amount <= 0) {
            throw new AccountException("Сумма пополнения должна быть положительной.");
        }

        Currency currency = Currency.valueOf(currencyStr.toUpperCase());
        Account account = getAccountByUserId(user.getId(), currency);

        account.setBalance(account.getBalance() + amount);

        accountRepository.save(account);
        transactionService.recordDeposit(account, amount);
    }

    @Override
    public void withdraw(long accountId, double amount) throws AccountNotFoundException, InsufficientFundsException {
        if (amount <= 0) {
            throw new AccountException("Сумма снятия должна быть положительной.");
        }

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Счет не найден."));

        if (account.getBalance() < amount) {
            throw new InsufficientFundsException("Недостаточно средств для снятия.");
        }

        account.setBalance(account.getBalance() - amount);

        accountRepository.save(account);
        transactionService.recordWithdrawal(account, amount);
    }

    @Override
    public Account getAccountByUserId(long userId, Currency currency) throws AccountException {
        List<Account> accounts = accountRepository.findByUserId(userId)
                .orElseThrow(() -> new AccountException("Не удалось получить список счетов пользователя."));

        return accounts.stream()
                .filter(account -> account.getCurrency().equals(currency))
                .findFirst()
                .orElseThrow(() -> new AccountException("Счет с указанной валютой не найден."));
    }

    @Override
    public Account getLatestAccountByUserId(long userId) throws AccountException {
        List<Account> accounts = accountRepository.findByUserId(userId)
                .orElseThrow(() -> new AccountException("Не удалось получить список счетов пользователя."));

        if (accounts.isEmpty()) {
            throw new AccountException("У пользователя нет счетов.");
        }

        return accounts.stream()
                .sorted(Comparator.comparing(Account::getCreationDate).reversed())
                .findFirst()
                .orElseThrow(() -> new AccountException("Не удалось найти последний счет."));
    }

    @Override
    public Account getLatestAccountByUserId(long userId, Currency currency) throws AccountException {
        List<Account> accounts = accountRepository.findByUserId(userId)
                .orElseThrow(() -> new AccountException("Не удалось получить список счетов пользователя."));

        List<Account> filteredAccounts = accounts.stream()
                .filter(account -> account.getCurrency().equals(currency))
                .collect(Collectors.toList());

        if (filteredAccounts.isEmpty()) {
            throw new AccountException("У пользователя нет счетов с указанной валютой.");
        }

        return filteredAccounts.get(filteredAccounts.size() - 1);
    }
}
