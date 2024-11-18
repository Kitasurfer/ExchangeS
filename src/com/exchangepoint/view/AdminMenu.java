package com.exchangepoint.view;

import com.exchangepoint.model.User;
import com.exchangepoint.model.Currency;
import com.exchangepoint.service.AdminService;
import com.exchangepoint.exception.UserNotFoundException;

import java.util.Scanner;

public class AdminMenu {
    private final AdminService adminService;
    private final Scanner scanner;

    public AdminMenu(AdminService adminService) {
        this.adminService = adminService;
        this.scanner = new Scanner(System.in);
    }

    public void show(User user) {
        while (true) {
            System.out.println("Меню администратора:");
            System.out.println("1. Установить курс валют");
            System.out.println("2. Заблокировать пользователя");
            System.out.println("3. Разблокировать пользователя");
            System.out.println("4. Просмотреть всех пользователей");
            System.out.println("5. Выйти");
            System.out.print("Выберите действие: ");

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
                    getAllUsers();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private void getAllUsers() {
        adminService.getUsers().forEach(System.out::println);
    }

    private void setExchangeRate() {
        System.out.print("Введите валюту из которой конвертировать: ");
        String fromCurrencyStr = scanner.nextLine();
        System.out.print("Введите валюту в которую конвертировать: ");
        String toCurrencyStr = scanner.nextLine();
        System.out.print("Введите новый курс: ");
        double rate = Double.parseDouble(scanner.nextLine());

        try {
            Currency fromCurrency = Currency.valueOf(fromCurrencyStr.toUpperCase());
            Currency toCurrency = Currency.valueOf(toCurrencyStr.toUpperCase());
            adminService.setExchangeRate(fromCurrency, toCurrency, rate);
            System.out.println("Курс успешно обновлен.");
        } catch (IllegalArgumentException e) {
            System.out.println("Неверная валюта.");
        }
    }

    private void blockUser() {
        System.out.print("Введите ID пользователя для блокировки: ");
        long userId = Long.parseLong(scanner.nextLine());
        try {
            adminService.blockUser(userId);
            System.out.println("Пользователь заблокирован.");
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void unblockUser() {
        System.out.print("Введите ID пользователя для разблокировки: ");
        long userId = Long.parseLong(scanner.nextLine());
        try {
            adminService.unblockUser(userId);
            System.out.println("Пользователь разблокирован.");
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
