package com.exchangepoint.view;

import com.exchangepoint.model.User;
import com.exchangepoint.model.Role;
import com.exchangepoint.service.UserService;
import com.exchangepoint.exception.ValidationException;

import java.util.Map;
import java.util.Scanner;

public class MainMenu {
    private final UserService userService;
    private final UserMenu userMenu;
    private final AdminMenu adminMenu;
    private final Scanner scanner;
    private final Map<String, String> messages;

    public MainMenu(UserService userService, UserMenu userMenu, AdminMenu adminMenu, Map<String, String> messages) {
        this.userService = userService;
        this.userMenu = userMenu;
        this.adminMenu = adminMenu;
        this.scanner = new Scanner(System.in);
        this.messages = messages;
    }

    public void show() {
        while (true) {
            System.out.println(messages.get("welcome.exchangepoint"));
            System.out.println(messages.get("menu.option1"));
            System.out.println(messages.get("menu.option2"));
            System.out.println(messages.get("menu.option3"));
            System.out.print(messages.get("menu.choose"));

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    register();
                    break;
                case "2":
                    login();
                    break;
                case "3":
                    System.out.println(messages.get("farewell"));
                    return;
                default:
                    System.out.println(messages.get("menu.invalid"));
            }
        }
    }

    private void register() {
        try {
            System.out.print(messages.get("enter.name"));
            String name = scanner.nextLine();
            System.out.print(messages.get("enter.email"));
            String email = scanner.nextLine();
            System.out.print(messages.get("enter.password"));
            String password = scanner.nextLine();

            User user = new User(name, email, password);
            userService.register(user);
            System.out.println(messages.get("registration.success"));

        } catch (ValidationException e) {
            System.out.println(messages.get("registration.error") + e.getMessage());
        }
    }

    private void login() {
        try {
            System.out.print(messages.get("enter.email"));
            String email = scanner.nextLine();
            System.out.print(messages.get("enter.password"));
            String password = scanner.nextLine();

            User user = userService.login(email, password);
            System.out.println(messages.get("login.success"));

            if (user.getRoles().contains(Role.ADMIN)) {
                adminMenu.show(user);
            } else {
                userMenu.show(user);
            }

        } catch (ValidationException e) {
            System.out.println(messages.get("login.error") + e.getMessage());
        }
    }
}

