package com.exchangepoint.view;

import com.exchangepoint.exception.AccountException;
import com.exchangepoint.exception.AccountNotFoundException;
import com.exchangepoint.exception.CurrencyExchangeException;
import com.exchangepoint.exception.InsufficientFundsException;
import com.exchangepoint.model.Currency;
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

    public void show() {
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
                    openAccount();
                    break;
                case "2":
                    deposit();
                    break;
                case "3":
                    withdraw();
                    break;
                case "4":
                    handleExchange();
                    break;
                case "5":
                    viewTransactions();
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private void openAccount() {
        System.out.println("Доступные валюты: EUR, USD, UAH, BTC, ETH");
        System.out.print("Введите валюту счета: ");
        String currencyStr = scanner.nextLine();
        try {
            Currency currency = Currency.valueOf(currencyStr.toUpperCase());
            accountService.openAccount(1L, currency); // Здесь используйте ID текущего пользователя
            System.out.println("Счет успешно открыт.");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: неверная валюта.");
        } catch (AccountException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private void deposit() {
        try {
            System.out.print("Введите ID счета: ");
            long accountId = Long.parseLong(scanner.nextLine());
            System.out.print("Введите сумму для пополнения: ");
            double amount = Double.parseDouble(scanner.nextLine());

            accountService.deposit(accountId, amount);
            System.out.println("Счет успешно пополнен.");
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: некорректный ввод числа.");
        } catch (AccountNotFoundException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (AccountException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private void withdraw() {
        try {
            System.out.print("Введите ID счета: ");
            long accountId = Long.parseLong(scanner.nextLine());
            System.out.print("Введите сумму для снятия: ");
            double amount = Double.parseDouble(scanner.nextLine());

            accountService.withdraw(accountId, amount);
            System.out.println("Средства успешно сняты.");
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: некорректный ввод числа.");
        } catch (AccountNotFoundException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (InsufficientFundsException e) {
            System.out.println("Ошибка: недостаточно средств.");
        } catch (AccountException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private void handleExchange() {
        System.out.println("Обмен валют:");
        System.out.println("1. Прямой обмен");
        System.out.println("2. Обратный обмен");
        System.out.print("Выберите тип обмена: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                directExchange();
                break;
            case "2":
                reverseExchange();
                break;
            default:
                System.out.println("Ошибка: неверный выбор.");
        }
    }

    private void directExchange() {
        try {
            System.out.print("Введите сумму: ");
            double amount = Double.parseDouble(scanner.nextLine());
            System.out.print("Введите исходную валюту (например, USD): ");
            String from = scanner.nextLine().toUpperCase();
            System.out.print("Введите целевую валюту (например, EUR): ");
            String to = scanner.nextLine().toUpperCase();

            double result = exchangeService.convertDirect(amount, Currency.valueOf(from), Currency.valueOf(to));
            System.out.printf("Результат обмена: %.2f %s\n", result, to);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: некорректный ввод числа.");
        } catch (CurrencyExchangeException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: неверная валюта.");
        }
    }

    private void reverseExchange() {
        try {
            System.out.print("Введите сумму: ");
            double amount = Double.parseDouble(scanner.nextLine());
            System.out.print("Введите целевую валюту (например, USD): ");
            String to = scanner.nextLine().toUpperCase();
            System.out.print("Введите исходную валюту (например, EUR): ");
            String from = scanner.nextLine().toUpperCase();

            double result = exchangeService.convertReverse(amount, Currency.valueOf(to), Currency.valueOf(from));
            System.out.printf("Результат обмена: %.2f %s\n", result, from);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: некорректный ввод числа.");
        } catch (CurrencyExchangeException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: неверная валюта.");
        }
    }

    private void viewTransactions() {
        try {
            System.out.println("История операций:");
            // Пример вызова транзакционного сервиса
            transactionService.getTransactions().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Ошибка при получении истории операций: " + e.getMessage());
        }
    }
}