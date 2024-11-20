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

import java.util.Scanner;

public class UserMenu {
    private final AccountService accountService;
    private final ExchangeService exchangeService;
    private final TransactionService transactionService;
    private final Scanner scanner;

    public UserMenu(AccountService accountService, ExchangeService exchangeService, TransactionService transactionService) {
        this.accountService = accountService;
        this.exchangeService = exchangeService;
        this.transactionService = transactionService;
        this.scanner = new Scanner(System.in);
    }

    public void show(User user) {
        while (true) {
            System.out.println("Меню пользователя:");
            System.out.println("1. Открыть счет");
            System.out.println("2. Пополнить счет");
            System.out.println("3. Снять средства");
            System.out.println("4. Обмен валют");
            System.out.println("5. Посмотреть историю операций");
            System.out.println("6. Выйти");
            System.out.print("Выберите действие: ");

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
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private void openAccount(User user) {
        System.out.println("Доступные валюты: EUR, USD, UAH, BTC, ETH");
        System.out.print("Введите валюту счета: ");
        String currencyStr = scanner.nextLine();
        try {
            Currency currency = Currency.valueOf(currencyStr.toUpperCase());
            accountService.openAccount(user.getId(), currency);
            Account newAccount = accountService.getLatestAccountByUserId(user.getId());
            System.out.println("Счет успешно открыт: " + newAccount);
        } catch (IllegalArgumentException e) {
            System.out.println("Неверная валюта.");
        } catch (AccountException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deposit(User user) {
        try {
            System.out.print("Введите сумму для пополнения: ");
            double amount = Double.parseDouble(scanner.nextLine());
            System.out.print("Введите валюту для пополнения: ");
            String currencyStr = scanner.nextLine();

            accountService.deposit(user, amount, currencyStr);
            Account updatedAccount = accountService.getAccountByUserId(user.getId(), Currency.valueOf(currencyStr.toUpperCase()));
            System.out.println("Пополнение успешно выполнено. Текущий баланс: " + updatedAccount.getBalance() + " " + updatedAccount.getCurrency());

        } catch (NumberFormatException e) {
            System.out.println("Вы ввели неправильную сумму.");
        } catch (IllegalArgumentException e) {
            System.out.println("Вы ввели несуществующую валюту для пополнения.");
        } catch (AccountNotFoundException | AccountException e) {
            System.out.println(e.getMessage());
        }
    }

    private void withdraw(User user) {
        try {
            System.out.print("Введите сумму для снятия: ");
            double amount = Double.parseDouble(scanner.nextLine());

            System.out.print("Введите валюту для снятия: ");
            String currencyStr = scanner.nextLine();
            Currency currency = Currency.valueOf(currencyStr.toUpperCase());

            Account account = accountService.getAccountByUserId(user.getId(), currency);
            accountService.withdraw(account.getId(), amount);
            Account updatedAccount = accountService.getAccountByUserId(user.getId(), currency);
            System.out.println("Снятие успешно выполнено. Текущий баланс: " + updatedAccount.getBalance() + " " + updatedAccount.getCurrency());

        } catch (NumberFormatException e) {
            System.out.println("Вы ввели неправильную сумму.");
        } catch (IllegalArgumentException e) {
            System.out.println("Вы ввели неверную валюту.");
        } catch (AccountNotFoundException | InsufficientFundsException | AccountException e) {
            System.out.println(e.getMessage());
        }
    }

    private void exchange(User user) {
        try {
            System.out.print("Введите ID счета для обмена: ");
            long fromAccountId = Long.parseLong(scanner.nextLine());
            System.out.print("Введите ID счета для зачисления: ");
            long toAccountId = Long.parseLong(scanner.nextLine());
            System.out.print("Введите сумму для обмена: ");
            double amount = Double.parseDouble(scanner.nextLine());

            exchangeService.exchange(fromAccountId, toAccountId, amount);
            Account fromAccount = accountService.getAccountByUserId(user.getId(), exchangeService.getFromCurrency(fromAccountId));
            Account toAccount = accountService.getAccountByUserId(user.getId(), exchangeService.getToCurrency(toAccountId));
            System.out.println("Обмен успешно выполнен.");
            System.out.println("Комиссия за транзакцию: " + amount * 0.002 + " " + fromAccount.getCurrency());
            System.out.println("Счет для обмена: " + fromAccount);
            System.out.println("Счет для зачисления: " + toAccount);
        } catch (NumberFormatException e) {
            System.out.println("Вы ввели неправильную сумму.");
        } catch (IllegalArgumentException e) {
            System.out.println("Неверный ID счета.");
        } catch (AccountException e) {
            System.out.println(e.getMessage());
        }
    }

    private void viewTransactions(User user) {
        System.out.println("История операций:");
        transactionService.getTransactions(user.getId())
                .forEach(transaction ->
                        System.out.println("ID операции: " + transaction.getId() +
                                ", Тип: " + transaction.getType() +
                                ", Сумма: " + transaction.getAmount() + " " + transaction.getCurrency() +
                                ", Баланс после операции: " + transaction.getBalanceAfter() +
                                ", Дата: " + transaction.getTimestamp()));
    }


}
