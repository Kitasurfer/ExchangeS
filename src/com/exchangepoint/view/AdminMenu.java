package com.exchangepoint.view;

import com.exchangepoint.exception.UserNotFoundException;
import com.exchangepoint.model.Role;
import com.exchangepoint.model.User;
import com.exchangepoint.model.Currency;
import com.exchangepoint.service.AdminService;

import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

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
            System.out.println("4. Добавить пользователя");
            System.out.println("5. Удалить пользователя");
            System.out.println("6. Обновить пользователя");
            System.out.println("7. Просмотреть всех пользователей");
            System.out.println("8. Выйти");
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

    private void addUser() {
        System.out.print("Введите имя пользователя: ");
        String name = scanner.nextLine();
        System.out.print("Введите email пользователя: ");
        String email = scanner.nextLine();
        System.out.print("Введите пароль пользователя: ");
        String password = scanner.nextLine();

        User user = new User(name, email, password);
        adminService.addUser(user);
        System.out.println("Пользователь успешно добавлен.");
    }

    private void deleteUser() {
        System.out.print("Введите ID пользователя для удаления: ");
        long userId = Long.parseLong(scanner.nextLine());
        try {
            adminService.deleteUserById(userId);
            System.out.println("Пользователь успешно удален.");
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateUser() {
        System.out.print("Введите ID пользователя для обновления: ");
        long userId = Long.parseLong(scanner.nextLine());
        System.out.print("Введите новое имя пользователя: ");
        String name = scanner.nextLine();
        System.out.print("Введите новый email пользователя: ");
        String email = scanner.nextLine();
        System.out.print("Введите новый пароль пользователя: ");
        String password = scanner.nextLine();
        System.out.print("Введите роли пользователя (через запятую): ");
        String rolesInput = scanner.nextLine();
        Set<Role> roles = Set.of(rolesInput.split(",")).stream()
                .map(String::trim)
                .map(Role::valueOf)
                .collect(Collectors.toSet());

        User user = new User(userId, name, email, password, roles);
        try {
            adminService.updateUser(user);
            System.out.println("Пользователь успешно обновлен.");
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }


}

