package com.exchangepoint;

import com.exchangepoint.model.User;
import com.exchangepoint.repository.*;
import com.exchangepoint.service.*;
import com.exchangepoint.view.AdminMenu;
import com.exchangepoint.view.UserMenu;

import java.io.*;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final String ADMIN_DATA_FILE = "DB/admin_data.txt";
    private static boolean isAdminRegistered = false;
    private static String adminUsername = "";
    private static String adminPassword = "";

    public static void main(String[] args) {
        // Чтение данных администратора из файла DB
        readAdminData();

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

        // Основное меню выбора режима
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Добро пожаловать в ExchangePoint!");
            System.out.println("1. Регистрация пользователя");
            System.out.println("2. Вход как пользователь");
            System.out.println("3. Вход как администратор");
            System.out.println("4. Выход");
            System.out.print("Выберите действие: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    // Логика для регистрации пользователя
                    System.out.println("Регистрация пользователя...");
                    // Здесь можно добавить вашу логику для регистрации пользователя
                    break;
                case "2":
                    // Вход в пользовательское меню
                    System.out.print("Введите ID пользователя: ");
                    long userId = Long.parseLong(scanner.nextLine());
                    Optional<User> userOptional = userService.findById(userId);
                    if (userOptional.isPresent()) {
                        userMenu.show(userOptional.get());
                    } else {
                        System.out.println("Пользователь не найден. Попробуйте снова.");
                    }
                    break;
                case "3":
                    // Вход как администратор
                    if (!isAdminRegistered) {
                        System.out.println("Администратор не зарегистрирован. Пожалуйста, зарегистрируйтесь.");
                        registerAdmin(scanner);
                    } else {
                        if (loginAdmin(scanner)) {
                            adminMenu.show(null); // Вход в админ меню
                        } else {
                            System.out.println("Неверное имя пользователя или пароль. Попробуйте снова.");
                        }
                    }
                    break;
                case "4":
                    // Завершение работы программы
                    System.out.println("Выход из приложения...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    // Метод для регистрации администратора
    private static void registerAdmin(Scanner scanner) {
        System.out.print("Введите имя администратора: ");
        adminUsername = scanner.nextLine();
        System.out.print("Введите пароль администратора: ");
        adminPassword = scanner.nextLine();
        isAdminRegistered = true;

        // Сохранение данных администратора в файл
        saveAdminData();
        System.out.println("Администратор успешно зарегистрирован.");
    }

    // Метод для входа администратора
    private static boolean loginAdmin(Scanner scanner) {
        System.out.print("Введите имя администратора: ");
        String username = scanner.nextLine();
        System.out.print("Введите пароль администратора: ");
        String password = scanner.nextLine();
        return adminUsername.equals(username) && adminPassword.equals(password);
    }

    // Метод для сохранения данных администратора в файл
    private static void saveAdminData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ADMIN_DATA_FILE))) {
            writer.write(adminUsername + "\n");
            writer.write(adminPassword + "\n");
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении данных администратора: " + e.getMessage());
        }
    }

    // Метод для чтения данных администратора из файла
    private static void readAdminData() {
        File file = new File(ADMIN_DATA_FILE);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(ADMIN_DATA_FILE))) {
                adminUsername = reader.readLine();
                adminPassword = reader.readLine();
                if (adminUsername != null && adminPassword != null) {
                    isAdminRegistered = true;
                }
            } catch (IOException e) {
                System.out.println("Ошибка при чтении данных администратора: " + e.getMessage());
            }
        }
    }
}
