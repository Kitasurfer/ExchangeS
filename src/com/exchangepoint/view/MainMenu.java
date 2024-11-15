package com.exchangepoint.view;

/**
 * Group: 52-1, "AIT Hi-tech team" GMBH
 * Author: Bogdan Fesenko
 * Date: 15-11-2024
 */
/*

 */


import com.exchangepoint.model.User;
import com.exchangepoint.model.Role;
import com.exchangepoint.service.UserService;
import com.exchangepoint.exception.ValidationException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MainMenu {
    private UserService userService;
    private UserMenu userMenu;
    private AdminMenu adminMenu;
    private Scanner scanner;

    public MainMenu(UserService userService, UserMenu userMenu, AdminMenu adminMenu) {
        this.userService = userService;
        this.userMenu = userMenu;
        this.adminMenu = adminMenu;
        this.scanner = new Scanner(System.in);
    }

    public void show() {
        while (true) {
            System.out.println("Добро пожаловать в ExchangePoint!");
            System.out.println("1. Регистрация");
            System.out.println("2. Вход");
            System.out.println("3. Выход");
            System.out.print("Выберите действие: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    register();
                    break;
                case "2":
                    login();
                    break;
                case "3":
                    System.out.println("До свидания!");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private void register() {
        try {
            System.out.print("Введите имя: ");
            String name = scanner.nextLine();
            System.out.print("Введите email: ");
            String email = scanner.nextLine();
            System.out.print("Введите пароль: ");
            String password = scanner.nextLine();


            User user = new User(name, email, password);
            userService.register(user);
            System.out.println("Регистрация успешна!");

        } catch (ValidationException e) {
            System.out.println("Ошибка регистрации: " + e.getMessage());
        }
    }

    private void login() {
        try {
            System.out.print("Введите email: ");
            String email = scanner.nextLine();
            System.out.print("Введите пароль: ");
            String password = scanner.nextLine();

            User user = userService.login(email, password);
            System.out.println("Вход выполнен!");

            if (user.getRoles().contains(Role.ADMIN)) {
                adminMenu.show(user);
            } else {
                userMenu.show(user);
            }

        } catch (ValidationException e) {
            System.out.println("Ошибка входа: " + e.getMessage());
        }
    }
}
