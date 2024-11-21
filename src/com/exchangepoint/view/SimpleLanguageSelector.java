package com.exchangepoint.view;

import java.util.HashMap;
import java.util.Map;

public class SimpleLanguageSelector {
    public static final Map<String, String> englishTexts = new HashMap<>();
    public static final Map<String, String> russianTexts = new HashMap<>();
    public static final Map<String, String> germanTexts = new HashMap<>();
    public static final Map<Integer, Map<String, String>> languages = new HashMap<>();

    static {
        // Английский
        englishTexts.put("menu.user.welcome", "User Menu:");
        englishTexts.put("menu.user.option1", "1. Open Account");
        englishTexts.put("menu.user.option2", "2. Deposit");
        englishTexts.put("menu.user.option3", "3. Withdraw");
        englishTexts.put("menu.user.option4", "4. Exchange Currency");
        englishTexts.put("menu.user.option5", "5. View Transaction History");
        englishTexts.put("menu.user.option6", "6. Exit");
        englishTexts.put("menu.admin.welcome", "Admin Menu:");
        englishTexts.put("menu.admin.option1", "1. Set Exchange Rate");
        englishTexts.put("menu.admin.option2", "2. Block User");
        englishTexts.put("menu.admin.option3", "3. Unblock User");
        englishTexts.put("menu.admin.option4", "4. Add User");
        englishTexts.put("menu.admin.option5", "5. Delete User");
        englishTexts.put("menu.admin.option6", "6. Update User");
        englishTexts.put("menu.admin.option7", "7. View All Users");
        englishTexts.put("menu.admin.option8", "8. View User Transactions");
        englishTexts.put("menu.admin.option9", "9. View User Accounts");
        englishTexts.put("menu.admin.option10", "10. Add User Account");
        englishTexts.put("menu.admin.option11", "11. Delete User Account");
        englishTexts.put("menu.admin.option12", "12. Exit");
        englishTexts.put("available.currencies", "Available currencies: EUR, USD, UAH, BTC, ETH");
        englishTexts.put("enter.currency", "Enter account currency:");
        englishTexts.put("account.opened", "Account successfully opened:");
        englishTexts.put("invalid.currency", "Invalid currency.");
        englishTexts.put("invalid.amount", "Invalid amount.");
        englishTexts.put("invalid.deposit.currency", "Invalid currency for deposit.");
        englishTexts.put("deposit.success", "Deposit successful. Current balance:");
        englishTexts.put("enter.deposit.amount", "Enter deposit amount:");
        englishTexts.put("enter.deposit.currency", "Enter deposit currency:");
        englishTexts.put("enter.withdraw.amount", "Enter withdrawal amount:");
        englishTexts.put("enter.withdraw.currency", "Enter withdrawal currency:");
        englishTexts.put("withdraw.success", "Withdrawal successful. Current balance:");
        englishTexts.put("enter.exchange.from", "Enter source account ID:");
        englishTexts.put("enter.exchange.to", "Enter destination account ID:");
        englishTexts.put("enter.exchange.amount", "Enter amount to exchange:");
        englishTexts.put("exchange.success", "Exchange successful.");
        englishTexts.put("exchange.fee", "Transaction fee:");
        englishTexts.put("exchange.from.account", "Source account:");
        englishTexts.put("exchange.to.account", "Destination account:");
        englishTexts.put("transaction.history", "Transaction history:");
        englishTexts.put("transaction.id", "Transaction ID:");
        englishTexts.put("transaction.type", "Type:");
        englishTexts.put("transaction.amount", "Amount:");
        englishTexts.put("transaction.balance.after", "Balance after:");
        englishTexts.put("transaction.date", "Date:");
        englishTexts.put("welcome.exchangepoint", "Welcome to ExchangePoint!");
        englishTexts.put("menu.option1", "1. Register");
        englishTexts.put("menu.option2", "2. Login");
        englishTexts.put("menu.option3", "3. Exit");
        englishTexts.put("menu.choose", "Choose an action:");
        englishTexts.put("farewell", "Goodbye!");
        englishTexts.put("menu.invalid", "Invalid choice. Please try again.");
        englishTexts.put("enter.name", "Enter your name: ");
        englishTexts.put("enter.email", "Enter your email: ");
        englishTexts.put("enter.password", "Enter your password: ");
        englishTexts.put("registration.success", "Registration successful!");
        englishTexts.put("registration.error", "Registration error: ");
        englishTexts.put("login.success", "Login successful!");
        englishTexts.put("login.error", "Login error: ");
        englishTexts.put("enter.exchange.from.currency", "Enter the currency to exchange from: ");
        englishTexts.put("enter.exchange.to.currency", "Enter the currency to exchange to: ");
        englishTexts.put("account.not.found", "Account not found for the specified currency.");
        englishTexts.put("transaction.details", "Transaction ID: %d, Type: %s, Amount: %.2f %s, Balance after: %.2f, Date: %s%n");




        // Русский
        russianTexts.put("menu.user.welcome", "Меню пользователя:");
        russianTexts.put("menu.user.option1", "1. Открыть счет");
        russianTexts.put("menu.user.option2", "2. Пополнить счет");
        russianTexts.put("menu.user.option3", "3. Снять средства");
        russianTexts.put("menu.user.option4", "4. Обмен валют");
        russianTexts.put("menu.user.option5", "5. Посмотреть историю операций");
        russianTexts.put("menu.user.option6", "6. Выйти");
        russianTexts.put("menu.admin.welcome", "Меню администратора:");
        russianTexts.put("menu.admin.option1", "1. Установить курс валют");
        russianTexts.put("menu.admin.option2", "2. Заблокировать пользователя");
        russianTexts.put("menu.admin.option3", "3. Разблокировать пользователя");
        russianTexts.put("menu.admin.option4", "4. Добавить пользователя");
        russianTexts.put("menu.admin.option5", "5. Удалить пользователя");
        russianTexts.put("menu.admin.option6", "6. Обновить пользователя");
        russianTexts.put("menu.admin.option7", "7. Просмотреть всех пользователей");
        russianTexts.put("menu.admin.option8", "8. Просмотреть все транзакции пользователя");
        russianTexts.put("menu.admin.option9", "9. Просмотреть все счета пользователя");
        russianTexts.put("menu.admin.option10", "10. Добавить новый счет для пользователя");
        russianTexts.put("menu.admin.option11", "11. Удалить счет пользователя");
        russianTexts.put("menu.admin.option12", "12. Выйти");
        russianTexts.put("menu.choose", "Выберите действие:");
        russianTexts.put("menu.invalid", "Неверный выбор. Попробуйте снова.");
        russianTexts.put("available.currencies", "Доступные валюты: EUR, USD, UAH, BTC, ETH");
        russianTexts.put("enter.currency", "Введите валюту счета:");
        russianTexts.put("account.opened", "Счет успешно открыт:");
        russianTexts.put("invalid.currency", "Неверная валюта.");
        russianTexts.put("invalid.amount", "Вы ввели неправильную сумму.");
        russianTexts.put("invalid.deposit.currency", "Вы ввели несуществующую валюту для пополнения.");
        russianTexts.put("deposit.success", "Пополнение успешно выполнено. Текущий баланс:");
        russianTexts.put("enter.deposit.amount", "Введите сумму для пополнения:");
        russianTexts.put("enter.deposit.currency", "Введите валюту для пополнения:");
        russianTexts.put("enter.withdraw.amount", "Введите сумму для снятия:");
        russianTexts.put("enter.withdraw.currency", "Введите валюту для снятия:");
        russianTexts.put("withdraw.success", "Снятие успешно выполнено. Текущий баланс:");
        russianTexts.put("enter.exchange.from", "Введите ID счета для обмена:");
        russianTexts.put("enter.exchange.to", "Введите ID счета для зачисления:");
        russianTexts.put("enter.exchange.amount", "Введите сумму для обмена:");
        russianTexts.put("exchange.success", "Обмен успешно выполнен.");
        russianTexts.put("exchange.fee", "Комиссия за транзакцию:");
        russianTexts.put("exchange.from.account", "Счет для обмена:");
        russianTexts.put("exchange.to.account", "Счет для зачисления:");
        russianTexts.put("transaction.history", "История операций:");
        russianTexts.put("transaction.id", "ID операции:");
        russianTexts.put("transaction.type", "Тип:");
        russianTexts.put("transaction.amount", "Сумма:");
        russianTexts.put("transaction.balance.after", "Баланс после операции:");
        russianTexts.put("transaction.date", "Дата:");
        russianTexts.put("welcome.exchangepoint", "Добро пожаловать в ExchangePoint!");
        russianTexts.put("menu.option1", "1. Регистрация");
        russianTexts.put("menu.option2", "2. Вход");
        russianTexts.put("menu.option3", "3. Выход");
        russianTexts.put("farewell", "До свидания!");
        russianTexts.put("enter.name", "Введите имя: ");
        russianTexts.put("enter.email", "Введите email: ");
        russianTexts.put("enter.password", "Введите пароль: ");
        russianTexts.put("registration.success", "Регистрация успешна!");
        russianTexts.put("registration.error", "Ошибка регистрации: ");
        russianTexts.put("login.success", "Вход выполнен!");
        russianTexts.put("login.error", "Ошибка входа: ");
        russianTexts.put("enter.exchange.from.currency", "Введите валюту для обмена: ");
        russianTexts.put("enter.exchange.to.currency", "Введите валюту для зачисления: ");
        russianTexts.put("account.not.found", "Счет для указанной валюты не найден.");
        russianTexts.put("transaction.details", "ID операции: %d, Тип: %s, Сумма: %.2f %s, Баланс после операции: %.2f, Дата: %s%n");




        // Немецкий
        germanTexts.put("menu.user.welcome", "Benutzermenü:");
        germanTexts.put("menu.user.option1", "1. Konto eröffnen");
        germanTexts.put("menu.user.option2", "2. Einzahlung");
        germanTexts.put("menu.user.option3", "3. Auszahlung");
        germanTexts.put("menu.user.option4", "4. Währung wechseln");
        germanTexts.put("menu.user.option5", "5. Transaktionsverlauf anzeigen");
        germanTexts.put("menu.user.option6", "6. Beenden");
        germanTexts.put("menu.admin.welcome", "Administrator-Menü:");
        germanTexts.put("menu.admin.option1", "1. Wechselkurs festlegen");
        germanTexts.put("menu.admin.option2", "2. Benutzer sperren");
        germanTexts.put("menu.admin.option3", "3. Benutzer entsperren");
        germanTexts.put("menu.admin.option4", "4. Benutzer hinzufügen");
        germanTexts.put("menu.admin.option5", "5. Benutzer löschen");
        germanTexts.put("menu.admin.option6", "6. Benutzer aktualisieren");
        germanTexts.put("menu.admin.option7", "7. Alle Benutzer anzeigen");
        germanTexts.put("menu.admin.option8", "8. Benutzertransaktionen anzeigen");
        germanTexts.put("menu.admin.option9", "9. Benutzerkonten anzeigen");
        germanTexts.put("menu.admin.option10", "10. Benutzerkonto hinzufügen");
        germanTexts.put("menu.admin.option11", "11. Benutzerkonto löschen");
        germanTexts.put("menu.admin.option12", "12. Beenden");
        germanTexts.put("menu.choose", "Wählen Sie eine Aktion:");
        germanTexts.put("menu.invalid", "Ungültige Wahl. Bitte versuchen Sie es erneut.");
        germanTexts.put("available.currencies", "Verfügbare Währungen: EUR, USD, UAH, BTC, ETH");
        germanTexts.put("enter.currency", "Geben Sie die Währung des Kontos ein:");
        germanTexts.put("account.opened", "Konto erfolgreich eröffnet:");
        germanTexts.put("invalid.currency", "Ungültige Währung.");
        germanTexts.put("invalid.amount", "Ungültiger Betrag.");
        germanTexts.put("invalid.deposit.currency", "Ungültige Einzahlung Währung.");
        germanTexts.put("deposit.success", "Einzahlung erfolgreich. Aktueller Kontostand:");
        germanTexts.put("enter.deposit.amount", "Geben Sie den Einzahlungsbetrag ein:");
        germanTexts.put("enter.deposit.currency", "Geben Sie die Einzahlung Währung ein:");
        germanTexts.put("enter.withdraw.amount", "Geben Sie den Abhebungsbetrag ein:");
        germanTexts.put("enter.withdraw.currency", "Geben Sie die Abhebungswährung ein:");
        germanTexts.put("withdraw.success", "Abhebung erfolgreich. Aktueller Kontostand:");
        germanTexts.put("enter.exchange.from", "Geben Sie die Quellkonto-ID ein:");
        germanTexts.put("enter.exchange.to", "Geben Sie die Zielkonto-ID ein:");
        germanTexts.put("enter.exchange.amount", "Geben Sie den Wechselbetrag ein:");
        germanTexts.put("exchange.success", "Wechsel erfolgreich.");
        germanTexts.put("exchange.fee", "Transaktionsgebühr:");
        germanTexts.put("exchange.from.account", "Quellkonto:");
        germanTexts.put("exchange.to.account", "Zielkonto:");
        germanTexts.put("transaction.history", "Transaktionsverlauf:");
        germanTexts.put("transaction.id", "Transaktions-ID:");
        germanTexts.put("transaction.type", "Typ:");
        germanTexts.put("transaction.amount", "Betrag:");
        germanTexts.put("transaction.balance.after", "Kontostand nach:");
        germanTexts.put("transaction.date", "Datum:");
        germanTexts.put("welcome.exchangepoint", "Willkommen bei ExchangePoint!");
        germanTexts.put("menu.option1", "1. Registrieren");
        germanTexts.put("menu.option2", "2. Anmelden");
        germanTexts.put("menu.option3", "3. Beenden");
        germanTexts.put("farewell", "Auf Wiedersehen!");
        germanTexts.put("enter.name", "Geben Sie Ihren Namen ein: ");
        germanTexts.put("enter.email", "Geben Sie Ihre E-Mail-Adresse ein: ");
        germanTexts.put("enter.password", "Geben Sie Ihr Passwort ein: ");
        germanTexts.put("registration.success", "Registrierung erfolgreich!");
        germanTexts.put("registration.error", "Registrierungsfehler: ");
        germanTexts.put("login.success", "Anmeldung erfolgreich!");
        germanTexts.put("login.error", "Anmeldefehler: ");
        germanTexts.put("enter.exchange.from.currency", "Geben Sie die Währung zum Austausch ein: ");
        germanTexts.put("enter.exchange.to.currency", "Geben Sie die Währung für die Gutschrift ein: ");
        germanTexts.put("account.not.found", "Konto für die angegebene Währung nicht gefunden.");
        germanTexts.put("transaction.details", "Transaktions-ID: %d, Typ: %s, Betrag: %.2f %s, Kontostand nach: %.2f, Datum: %s%n");


        // Добавление мапов в общую мапу языков
        languages.put(1, englishTexts);
        languages.put(2, russianTexts);
        languages.put(3, germanTexts);

        }
    }

