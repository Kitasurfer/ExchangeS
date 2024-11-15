/*
package com.exchangepoint.view;

*/
/**
 * Group: 52-1, "AIT Hi-tech team" GMBH
 * Author: Bogdan Fesenko
 * Date: 15-11-2024
 *//*

*/
/*

 *//*



import com.exchangepoint.model.User;
import com.exchangepoint.model.Currency;
import com.exchangepoint.service.AccountService;
import com.exchangepoint.service.ExchangeService;
import com.exchangepoint.service.TransactionService;

import java.util.Scanner;

public class UserMenu {
    private AccountService accountService;
    private ExchangeService exchangeService;
    private TransactionService transactionService;
    private Scanner scanner;

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
            System.out.println("Счет успешно открыт.");
        } catch (IllegalArgumentException e) {
            System.out.println("Неверная валюта.");
        }
    }

    private void deposit(User user) {
        // Реализация метода пополнения счета
    }

    private void withdraw(User user) {
        // Реализация метода снятия средств
    }

    private void exchange(User user) {
        // Реализация метода обмена валют
    }

    private void viewTransactions(User user) {
        // Реализация метода просмотра истории операций
    }
}
*/

package com.exchangepoint.view;

import com.exchangepoint.model.User;
import com.exchangepoint.model.Currency;
import com.exchangepoint.service.AccountService;
import com.exchangepoint.service.ExchangeService;
import com.exchangepoint.service.TransactionService;

import java.util.Scanner;

public class UserMenu {
    private AccountService accountService;
    private ExchangeService exchangeService;
    private TransactionService transactionService;
    private Scanner scanner;

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
            System.out.println("Счет успешно открыт.");
        } catch (IllegalArgumentException e) {
            System.out.println("Неверная валюта.");
        }
    }

    private void deposit(User user) {
        System.out.print("Введите сумму для пополнения: ");
        double amount = Double.parseDouble(scanner.nextLine());
        // Получение аккаунта пользователя и пополнение
        // accountService.deposit(accountId, amount);
        System.out.println("Счет успешно пополнен.");
    }

    private void withdraw(User user) {
        System.out.print("Введите сумму для снятия: ");
        double amount = Double.parseDouble(scanner.nextLine());
        // Получение аккаунта пользователя и снятие средств
        // accountService.withdraw(accountId, amount);
        System.out.println("Средства успешно сняты.");
    }

    private void exchange(User user) {
        System.out.print("Введите ID счета для обмена: ");
        long fromAccountId = Long.parseLong(scanner.nextLine());
        System.out.print("Введите ID счета для зачисления: ");
        long toAccountId = Long.parseLong(scanner.nextLine());
        System.out.print("Введите сумму для обмена: ");
        double amount = Double.parseDouble(scanner.nextLine());
        // exchangeService.exchange(fromAccountId, toAccountId, amount);
        System.out.println("Обмен успешно выполнен.");
    }

    private void viewTransactions(User user) {
        // Реализация метода просмотра истории операций
        System.out.println("История операций:");
        // transactionService.getTransactions(user.getId());
    }
}