package com.exchangepoint.service;

import com.exchangepoint.model.Account;
import com.exchangepoint.model.Currency;
import com.exchangepoint.repository.AccountRepository;
import com.exchangepoint.repository.ExchangeRateRepository;
import com.exchangepoint.exception.AccountNotFoundException;
import com.exchangepoint.exception.CurrencyExchangeException;
import com.exchangepoint.exception.InsufficientFundsException;


public class ExchangeServiceImpl implements ExchangeService {

    private final AccountRepository accountRepository;
    private final ExchangeRateRepository exchangeRateRepository;
    private final TransactionService transactionService;

    public ExchangeServiceImpl(AccountRepository accountRepository, ExchangeRateRepository exchangeRateRepository, TransactionService transactionService) {
        this.accountRepository = accountRepository;
        this.exchangeRateRepository = exchangeRateRepository;
        this.transactionService = transactionService;
    }

    @Override
    public void exchange(long fromAccountId, long toAccountId, double amount)
            throws AccountNotFoundException, CurrencyExchangeException, InsufficientFundsException {
        Account fromAccount = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new AccountNotFoundException("Счет списания не найден."));
        Account toAccount = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new AccountNotFoundException("Счет зачисления не найден."));
        if (fromAccount.getBalance() < amount) {
            throw new InsufficientFundsException("Недостаточно средств для обмена.");
        }
        Currency fromCurrency = fromAccount.getCurrency();
        Currency toCurrency = toAccount.getCurrency();
        double rate = exchangeRateRepository.getRate(fromCurrency, toCurrency);
        if (rate == 0.0) {
            throw new CurrencyExchangeException("Курс обмена не найден.");
        }
        double convertedAmount = amount * rate;
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + convertedAmount);
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
        transactionService.recordExchange(fromAccount, toAccount, amount, convertedAmount, rate);
    }
}
