package com.exchangepoint.service;

import com.exchangepoint.model.Account;
import com.exchangepoint.model.Currency;
import com.exchangepoint.repository.AccountRepository;
import com.exchangepoint.exception.AccountNotFoundException;
import com.exchangepoint.exception.InsufficientFundsException;


public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final TransactionService transactionService;

    public AccountServiceImpl(AccountRepository accountRepository, TransactionService transactionService) {
        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
    }

    @Override
    public void openAccount(long userId, Currency currency) {
        Account account = new Account(0, currency, 0.0, userId);
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
    public void deposit(long accountId, double amount) throws AccountNotFoundException {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Счет не найден."));
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
        transactionService.recordDeposit(account, amount);
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
}
