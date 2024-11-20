package com.exchangepoint;

import com.exchangepoint.model.Db;
import com.exchangepoint.repository.*;
import com.exchangepoint.service.*;
import com.exchangepoint.view.*;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Db db = new Db();

        // Создание репозиториев
        UserRepository userRepository = new UserRepositoryImpl(db);
        AccountRepository accountRepository = new AccountRepositoryImpl(db);
        ExchangeRateRepository exchangeRateRepository = new ExchangeRateRepositoryImpl(db);
        TransactionRepository transactionRepository = new TransactionRepositoryImpl(db);

        // Создание сервисов
        UserService userService = new UserServiceImpl(userRepository);
        TransactionService transactionService = new TransactionServiceImpl(transactionRepository);
        AccountService accountService = new AccountServiceImpl(accountRepository, transactionService);
        ExchangeService exchangeService = new ExchangeServiceImpl(accountRepository, exchangeRateRepository, transactionService);
        AdminService adminService = new AdminServiceImpl(transactionRepository, accountRepository, exchangeRateRepository, userRepository, db);

        // Запрос выбора языка у пользователя
        Scanner scanner = new Scanner(System.in);
        System.out.println("1 - English, 2 - Russian, 3 - German");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();  // consume the newline

        // Получаем выбранный словарь
        Map<String, String> selectedLanguage = SimpleLanguageSelector.languages.getOrDefault(choice, SimpleLanguageSelector.englishTexts);

        // Создание меню с учетом выбранного языка
        UserMenu userMenu = new UserMenu(accountService, exchangeService, transactionService, selectedLanguage);
        AdminMenu adminMenu = new AdminMenu(adminService, selectedLanguage);
        MainMenu mainMenu = new MainMenu(userService, userMenu, adminMenu, selectedLanguage);

        // Запуск приложения
        mainMenu.show();


    }
}



