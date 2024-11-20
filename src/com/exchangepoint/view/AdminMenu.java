package com.exchangepoint.view;

import com.exchangepoint.model.*;
import com.exchangepoint.service.AdminService;
import com.exchangepoint.exception.AccountNotFoundException;
import com.exchangepoint.exception.UserNotFoundException;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class AdminMenu {
    private final AdminService adminService;
    private final Scanner scanner;
    private final Map<String, String> messages;

    public AdminMenu(AdminService adminService, Map<String, String> messages) {
        this.adminService = adminService;
        this.scanner = new Scanner(System.in);
        this.messages = messages;
    }

    public void show(User user) {
        while (true) {
            System.out.println(messages.get("menu.admin.welcome"));
            System.out.println(messages.get("menu.admin.option1"));
            System.out.println(messages.get("menu.admin.option2"));
            System.out.println(messages.get("menu.admin.option3"));
            System.out.println(messages.get("menu.admin.option4"));
            System.out.println(messages.get("menu.admin.option5"));
            System.out.println(messages.get("menu.admin.option6"));
            System.out.println(messages.get("menu.admin.option7"));
            System.out.println(messages.get("menu.admin.option8"));
            System.out.println(messages.get("menu.admin.option9"));
            System.out.println(messages.get("menu.admin.option10"));
            System.out.println(messages.get("menu.admin.option11"));
            System.out.println(messages.get("menu.admin.option12"));
            System.out.print(messages.get("menu.choose"));

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    setExchangeRate();
                    break;
                case "2":
                    blockUser();
                    break;
                case "3":
                    unblockUser();
                    break;
                case "4":
                    addUser();
                    break;
                case "5":
                    deleteUser();
                    break;
                case "6":
                    updateUser();
                    break;
                case "7":
                    getAllUsers();
                    break;
                case "8":
                    getUserTransactions();
                    break;
                case "9":
                    getUserAccounts();
                    break;
                case "10":
                    addUserAccount();
                    break;
                case "11":
                    deleteUserAccount();
                    break;
                case "12":
                    return;
                default:
                    System.out.println(messages.get("menu.invalid"));
            }
        }
    }

    private void getAllUsers() {
        adminService.getUsers().forEach(System.out::println);
    }

    private void setExchangeRate() {
        System.out.print(messages.get("enter.from.currency"));
        String fromCurrencyStr = scanner.nextLine();
        System.out.print(messages.get("enter.to.currency"));
        String toCurrencyStr = scanner.nextLine();
        System.out.print(messages.get("enter.exchange.rate"));
        double rate = Double.parseDouble(scanner.nextLine());

        try {
            Currency fromCurrency = Currency.valueOf(fromCurrencyStr.toUpperCase());
            Currency toCurrency = Currency.valueOf(toCurrencyStr.toUpperCase());
            adminService.setExchangeRate(fromCurrency, toCurrency, rate);
            System.out.println(messages.get("exchange.rate.updated"));
        } catch (IllegalArgumentException e) {
            System.out.println(messages.get("invalid.currency"));
        }
    }

    private void blockUser() {
        System.out.print(messages.get("enter.user.id.block"));
        long userId = Long.parseLong(scanner.nextLine());
        try {
            adminService.blockUser(userId);
            System.out.println(messages.get("user.blocked"));
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void unblockUser() {
        System.out.print(messages.get("enter.user.id.unblock"));
        long userId = Long.parseLong(scanner.nextLine());
        try {
            adminService.unblockUser(userId);
            System.out.println(messages.get("user.unblocked"));
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addUser() {
        System.out.print(messages.get("enter.name"));
        String name = scanner.nextLine();
        System.out.print(messages.get("enter.email"));
        String email = scanner.nextLine();
        System.out.print(messages.get("enter.password"));
        String password = scanner.nextLine();

        User user = new User(name, email, password);
        adminService.addUser(user);
        System.out.println(messages.get("user.added"));
    }

    private void deleteUser() {
        System.out.print(messages.get("enter.user.id.delete"));
        long userId = Long.parseLong(scanner.nextLine());
        try {
            adminService.deleteUserById(userId);
            System.out.println(messages.get("user.deleted"));
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateUser() {
        System.out.print(messages.get("enter.user.id.update"));
        long userId = Long.parseLong(scanner.nextLine());
        System.out.print(messages.get("enter.new.name"));
        String name = scanner.nextLine();
        System.out.print(messages.get("enter.new.email"));
        String email = scanner.nextLine();
        System.out.print(messages.get("enter.new.password"));
        String password = scanner.nextLine();
        System.out.print(messages.get("enter.roles"));
        String rolesInput = scanner.nextLine();
        Set<Role> roles = Set.of(rolesInput.split(",")).stream()
                .map(String::trim)
                .map(Role::valueOf)
                .collect(Collectors.toSet());

        User user = new User(userId, name, email, password, roles);
        try {
            adminService.updateUser(user);
            System.out.println(messages.get("user.updated"));
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void getUserTransactions() {
        System.out.print(messages.get("enter.user.id.transactions"));
        long userId = Long.parseLong(scanner.nextLine());
        List<Transaction> transactions = adminService.getUserTransactions(userId);
        showUserTransactions(transactions);
    }

    private void showUserTransactions(List<Transaction> transactions) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        System.out.println(messages.get("transaction.history"));
        for (Transaction transaction : transactions) {
            String formattedDate = transaction.getTimestamp().format(formatter);
            System.out.printf(messages.get("transaction.details"),
                    transaction.getId(), transaction.getType(), transaction.getAmount(), transaction.getCurrency(), transaction.getBalanceAfter(), formattedDate);
        }
    }

    private void getUserAccounts() {
        System.out.print(messages.get("enter.user.id.accounts"));
        long userId = Long.parseLong(scanner.nextLine());
        List<Account> accounts = adminService.getUserAccounts(userId);
        accounts.forEach(System.out::println);
    }

    private void addUserAccount() {
        System.out.print(messages.get("enter.user.id.account"));
        long userId = Long.parseLong(scanner.nextLine());
        System.out.print(messages.get("enter.currency"));
        String currencyStr = scanner.nextLine();
        Currency currency = Currency.valueOf(currencyStr.toUpperCase());
        Account account = new Account(currency, userId);
        adminService.addAccount(account);
        System.out.println(messages.get("account.added"));
    }

    private void deleteUserAccount() {
        System.out.print(messages.get("enter.account.id.delete"));
        long accountId = Long.parseLong(scanner.nextLine());
        try {
            adminService.deleteAccount(accountId);
            System.out.println(messages.get("account.deleted"));
        } catch (AccountNotFoundException e) {
            System.out.println(messages.get("account.not.found") + e.getMessage());
        }
    }
}
