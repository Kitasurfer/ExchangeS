package com.exchangepoint.service;

import com.exchangepoint.exception.AccountException;
import com.exchangepoint.model.Account;
import com.exchangepoint.model.Currency;
import com.exchangepoint.model.Rate;
import com.exchangepoint.repository.AccountRepository;
import com.exchangepoint.repository.ExchangeRateRepository;

public class ExchangeServiceImpl implements ExchangeService {

    private final AccountRepository accountRepository;
    private final ExchangeRateRepository exchangeRateRepository;
    private final TransactionService transactionService;

    public ExchangeServiceImpl(AccountRepository accountRepository,
                               ExchangeRateRepository exchangeRateRepository,
                               TransactionService transactionService) {
        this.accountRepository = accountRepository;
        this.exchangeRateRepository = exchangeRateRepository; // Инициализация
        this.transactionService = transactionService;
    }

    @Override
    public double convertDirect(double amount, Currency from, Currency to) throws AccountException {
        // Логика конвертации валют (прямая)
        double rate = getExchangeRate(from, to);
        return amount * rate;
    }

    @Override
    public double convertReverse(double amount, Currency to, Currency from) throws AccountException {
        // Логика конвертации валют (обратная)
        double rate = getExchangeRate(from, to);
        return amount / rate;
    }

    @Override
    public void exchange(long fromAccountId, long toAccountId, double amount) throws AccountException {
        Account fromAccount = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new AccountException("Счет для списания не найден."));
        Account toAccount = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new AccountException("Счет для зачисления не найден."));

        if (fromAccount.getBalance() < amount) {
            throw new AccountException("Недостаточно средств для обмена.");
        }

        double rate = getExchangeRate(fromAccount.getCurrency(), toAccount.getCurrency());
        double convertedAmount = amount * rate;

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + convertedAmount);

        accountRepository.save(toAccount);
        accountRepository.save(fromAccount);
        // Регистрируем транзакцию обмена
        transactionService.recordExchange(fromAccount, toAccount, amount, convertedAmount, rate);
    }

    @Override
    public Currency getFromCurrency(long accountId) throws AccountException {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountException("Счет не найден."))
                .getCurrency();
    }

    @Override
    public Currency getToCurrency(long accountId) throws AccountException {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountException("Счет не найден."))
                .getCurrency();
    }

    private double getExchangeRate(Currency fromCurrency, Currency toCurrency) {
        String currencyPair = fromCurrency.name() + "-" + toCurrency.name();
        Rate rate = exchangeRateRepository.getRate(currencyPair);

        if (rate != null) {
            return rate.getRate();
        } else {
            throw new IllegalArgumentException("Курс обмена для пары " + currencyPair + " не найден.");
        }
    }

    public TransactionService getTransactionService() {
        return transactionService;
    }
}
