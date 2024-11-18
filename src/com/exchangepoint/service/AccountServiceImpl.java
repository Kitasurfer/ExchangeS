package com.exchangepoint.service;

import com.exchangepoint.exception.AccountException;
import com.exchangepoint.model.Account;
import com.exchangepoint.model.Currency;
import com.exchangepoint.model.User;
import com.exchangepoint.repository.AccountRepository;
import com.exchangepoint.exception.AccountNotFoundException;
import com.exchangepoint.exception.InsufficientFundsException;

import java.util.List;


public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final TransactionService transactionService;

    public AccountServiceImpl(AccountRepository accountRepository, TransactionService transactionService) {
        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
    }

    @Override
    public void openAccount(Long userId, Currency currency) {
        Account account = new Account(currency, userId);
        List<Account> accounts = accountRepository.findByUserId(userId).get();
        accounts.stream()
                .filter(ac -> ac.getCurrency().equals(currency)).findFirst()
                .ifPresent(ac -> {
                    throw new AccountException("Аккаунт с такой валютой уже существует");
        });
        accountRepository.save(account);
    }

    @Override
    public void closeAccount(long accountId) throws AccountNotFoundException {
        if (accountRepository.findById(accountId).isEmpty()) {
            throw new AccountNotFoundException("Счет не найден.");
        }
        accountRepository.delete(accountId);
    }

    @Override
    public void deposit(User user, double amount, String currencyStr) throws AccountNotFoundException {
        try {
            Long userId = user.getId();
            List<Account> accounts = accountRepository.findByUserId(userId).get();
            Currency currency = Currency.valueOf(currencyStr.toUpperCase());
            Account account = accounts.stream()
                    .filter(ac -> ac.getCurrency().equals(currency))
                    .findFirst()
                    .orElseThrow(() -> new AccountNotFoundException("Счет не найден."));

         //   if (!account.getCurrency().equals(currency)) {
          //      throw new AccountException("Вы ввели несуществующую валюту");
           // }
            account.setBalance(account.getBalance() + amount);
            accountRepository.save(account);
            transactionService.recordDeposit(account, amount);
        } catch (AccountException e) {
            throw new AccountNotFoundException(e.getMessage());
        }
    }

    @Override
    public void withdraw(long accountId, double amount) throws AccountNotFoundException, InsufficientFundsException {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Счет не найден."));
        if (account.getBalance() < amount) {
            throw new InsufficientFundsException("Недостаточно средств.");
        }
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
        transactionService.recordWithdrawal(account, amount);
    }

    @Override
    public Account getAccountByUserId(long userId, Currency currency) {
        List<Account> accounts = accountRepository.findByUserId(userId).get();
        return accounts.stream()
                .filter(account -> account.getCurrency().equals(currency))
                .findFirst()
                .orElse(null);
    }
}
