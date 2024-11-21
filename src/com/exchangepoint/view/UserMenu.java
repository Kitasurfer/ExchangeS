package com.exchangepoint.view;

import com.exchangepoint.exception.AccountException;
import com.exchangepoint.exception.AccountNotFoundException;
import com.exchangepoint.exception.InsufficientFundsException;
import com.exchangepoint.model.Account;
import com.exchangepoint.model.Currency;
import com.exchangepoint.model.User;
import com.exchangepoint.service.AccountService;
import com.exchangepoint.service.ExchangeService;
import com.exchangepoint.service.TransactionService;

import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;

public class UserMenu {
    private final AccountService accountService;
    private final ExchangeService exchangeService;
    private final TransactionService transactionService;
    private final Scanner scanner;
    private final Map<String, String> messages;

    public UserMenu(AccountService accountService, ExchangeService exchangeService, TransactionService transactionService, Map<String, String> messages) {
        this.accountService = accountService;
        this.exchangeService = exchangeService;
        this.transactionService = transactionService;
        this.scanner = new Scanner(System.in);
        this.messages = messages;
    }

    public void show(User user) {
        while (true) {
            System.out.println(messages.get("menu.user.welcome"));
            System.out.println(messages.get("menu.user.option1"));
            System.out.println(messages.get("menu.user.option2"));
            System.out.println(messages.get("menu.user.option3"));
            System.out.println(messages.get("menu.user.option4"));
            System.out.println(messages.get("menu.user.option5"));
            System.out.println(messages.get("menu.user.option6"));
            System.out.print(messages.get("menu.choose"));

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    openAccount(user);
                    break;
                case "2":
                    deposit(user);
                    break;
                case "3":
                    withdraw(user);
                    break;
                case "4":
                    exchange(user);
                    break;
                case "5":
                    viewTransactions(user);
                    break;
                case "6":
                    return;
                default:
                    System.out.println(messages.get("menu.invalid"));
            }
        }
    }

    private void openAccount(User user) {
        System.out.println(messages.get("available.currencies"));
        System.out.print(messages.get("enter.currency"));
        String currencyStr = scanner.nextLine();
        try {
            Currency currency = Currency.valueOf(currencyStr.toUpperCase());
            accountService.openAccount(user.getId(), currency);
            Account newAccount = accountService.getLatestAccountByUserId(user.getId());
            System.out.println(messages.get("account.opened") + " " + newAccount);
        } catch (IllegalArgumentException e) {
            System.out.println(messages.get("invalid.currency"));
        } catch (AccountException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deposit(User user) {
        try {
            System.out.print(messages.get("enter.deposit.amount"));
            double amount = Double.parseDouble(scanner.nextLine());
            System.out.print(messages.get("enter.deposit.currency"));
            String currencyStr = scanner.nextLine();

            accountService.deposit(user, amount, currencyStr);
            Account updatedAccount = accountService.getAccountByUserId(user.getId(), Currency.valueOf(currencyStr.toUpperCase()));
            System.out.println(messages.get("deposit.success") + " " + updatedAccount.getBalance() + " " + updatedAccount.getCurrency());

        } catch (NumberFormatException e) {
            System.out.println(messages.get("invalid.amount"));
        } catch (IllegalArgumentException e) {
            System.out.println(messages.get("invalid.deposit.currency"));
        } catch (AccountNotFoundException | AccountException e) {
            System.out.println(e.getMessage());
        }
    }

    private void withdraw(User user) {
        try {
            System.out.print(messages.get("enter.withdraw.amount"));
            double amount = Double.parseDouble(scanner.nextLine());

            System.out.print(messages.get("enter.withdraw.currency"));
            String currencyStr = scanner.nextLine();
            Currency currency = Currency.valueOf(currencyStr.toUpperCase());

            Account account = accountService.getAccountByUserId(user.getId(), currency);
            accountService.withdraw(account.getId(), amount);
            Account updatedAccount = accountService.getAccountByUserId(user.getId(), currency);
            System.out.println(messages.get("withdraw.success") + " " + updatedAccount.getBalance() + " " + updatedAccount.getCurrency());

        } catch (NumberFormatException e) {
            System.out.println(messages.get("invalid.amount"));
        } catch (IllegalArgumentException e) {
            System.out.println(messages.get("invalid.currency"));
        } catch (AccountNotFoundException | InsufficientFundsException | AccountException e) {
            System.out.println(e.getMessage());
        }
    }

    private void exchange(User user) {
        try {
            System.out.print(messages.get("enter.exchange.from.currency"));
            String fromCurrencyStr = scanner.nextLine().toUpperCase();
            System.out.print(messages.get("enter.exchange.to.currency"));
            String toCurrencyStr = scanner.nextLine().toUpperCase();
            System.out.print(messages.get("enter.exchange.amount"));
            double amount = Double.parseDouble(scanner.nextLine());

            Currency fromCurrency = Currency.valueOf(fromCurrencyStr);
            Currency toCurrency = Currency.valueOf(toCurrencyStr);

            // Найти аккаунты пользователя с соответствующими валютами
            Account fromAccount = accountService.getAccountByUserId(user.getId(), fromCurrency);
            Account toAccount = accountService.getAccountByUserId(user.getId(), toCurrency);

            // Выполнить обмен
            exchangeService.exchange(fromAccount.getId(), toAccount.getId(), amount);

            // Обновленные аккаунты
            fromAccount = accountService.getAccountByUserId(user.getId(), fromCurrency);
            toAccount = accountService.getAccountByUserId(user.getId(), toCurrency);

            System.out.println(messages.get("exchange.success"));
            System.out.println(messages.get("exchange.fee") + " " + amount * 0.002 + " " + fromAccount.getCurrency());
            System.out.println(messages.get("exchange.from.account") + " " + fromAccount);
            System.out.println(messages.get("exchange.to.account") + " " + toAccount);

        } catch (NumberFormatException e) {
            System.out.println(messages.get("invalid.amount"));
        } catch (IllegalArgumentException e) {
            System.out.println(messages.get("invalid.currency"));
        } catch (AccountException e) {
            System.out.println(e.getMessage());
        }
    }

    private void viewTransactions(User user) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        System.out.println(messages.get("transaction.history"));
        System.out.printf("%-15s %-20s %-15s %-20s %-20s %-20s%n", "ID операции", "Тип", "Сумма", "Валюта", "Баланс после", "Дата");
        System.out.println("=".repeat(115));
        transactionService.getTransactions(user.getId())
                .forEach(transaction -> {
                    String formattedDate = transaction.getTimestamp().format(formatter);
                    System.out.printf("%-15d %-20s %-15.2f %-20s %-20.2f %-20s%n",
                            transaction.getId(), transaction.getType(), transaction.getAmount(), transaction.getCurrency(), transaction.getBalanceAfter(), formattedDate);
                });
    }
}


