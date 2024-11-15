/**
 * Group: 52-1, "AIT Hi-tech team" GMBH
 * Author: Bogdan Fesenko
 * Date: 15-11-2024
 */
/*

 */


package com.exchangepoint;

import com.exchangepoint.repository.*;
import com.exchangepoint.service.*;
import com.exchangepoint.view.*;

public class Main {
    public static void main(String[] args) {
        // Создание репозиториев
        UserRepository userRepository = new UserRepositoryImpl();
        AccountRepository accountRepository = new AccountRepositoryImpl();
        ExchangeRateRepository exchangeRateRepository = new ExchangeRateRepositoryImpl();
        TransactionRepository transactionRepository = new TransactionRepositoryImpl();

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
