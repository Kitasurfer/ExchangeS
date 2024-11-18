package com.exchangepoint;

import com.exchangepoint.model.Db;
import com.exchangepoint.repository.*;
import com.exchangepoint.service.*;
import com.exchangepoint.view.*;

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
        AdminService adminService = new AdminServiceImpl(exchangeRateRepository, userRepository);

        // Создание меню
        UserMenu userMenu = new UserMenu(accountService, exchangeService, transactionService);
        AdminMenu adminMenu = new AdminMenu(adminService);
        MainMenu mainMenu = new MainMenu(userService, userMenu, adminMenu);

        // Запуск приложения
        mainMenu.show();
    }
}
