package com.exchangepoint.service;

import com.exchangepoint.exception.CurrencyExchangeException;
import com.exchangepoint.model.Currency;
import com.exchangepoint.model.Rate;
import com.exchangepoint.repository.AccountRepository;
import com.exchangepoint.repository.ExchangeRateRepository;

public class ExchangeServiceImpl implements ExchangeService {

    private final ExchangeRateRepository exchangeRateRepository;
    private final AccountRepository accountRepository;
    private final TransactionService transactionService;

    // Конструктор для репозитория обмена валют (основной)
    public ExchangeServiceImpl(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
        this.accountRepository = null; // Если не используется
        this.transactionService = null; // Если не используется
    }

    // Конструктор с accountRepository и transactionService
    public ExchangeServiceImpl(AccountRepository accountRepository, ExchangeRateRepository exchangeRateRepository, TransactionService transactionService) {
        this.accountRepository = accountRepository;
        this.exchangeRateRepository = exchangeRateRepository;
        this.transactionService = transactionService;
    }

    @Override
    public double convertDirect(double amount, Currency from, Currency to) throws CurrencyExchangeException {
        Rate rate = exchangeRateRepository.getRate(from, to);
        if (rate == null) {
            throw new CurrencyExchangeException("Курс обмена не найден для " + from + " -> " + to);
        }
        return amount * rate.getRate(); // Умножаем сумму на курс
    }

    @Override
    public double convertReverse(double amount, Currency to, Currency from) throws CurrencyExchangeException {
        Rate rate = exchangeRateRepository.getRate(from, to);
        if (rate == null) {
            throw new CurrencyExchangeException("Курс обмена не найден для " + from + " -> " + to);
        }
        return amount / rate.getRate(); // Делим сумму на курс
    }
}
