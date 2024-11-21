# Currency Exchange Office Project

**Project Description**  
The "Currency Exchange Office" is a console application designed to simulate the operations of a currency exchange office. It allows users to manage their accounts in multiple currencies, perform currency exchanges, deposit and withdraw funds, and view transaction history. The application is built on a multi-layer architecture with clearly defined Model, Service, and Repository layers. It offers both user and administrative functionalities, ensuring a comprehensive and realistic simulation of a currency exchange office.

---

## Technical Features

### 1. Multi-Layer Architecture
- **Model Layer:**
  - Includes entities such as `User`, `Account`, `Transaction`, `Currency`, `ExchangeRate`, and `Role`.
- **Service Layer:**
  - Handles business logic for managing accounts, performing transactions, and validating user data.
- **Repository Layer:**
  - Provides data storage and retrieval mechanisms (e.g., in-memory or file-based).

---

### 2. User Functionality
- Register a new user with email and password validation.
- Log into an account.
- View balance (all accounts or a specific account).
- Deposit funds in a selected currency:
  - If the account in the specified currency does not exist, open a new account.
- Withdraw funds from an account:
  - Includes checks for transaction feasibility.
- Open a new account.
- Close an account:
  - Validate whether the account has funds before proceeding.
- View transaction history:
  - For all accounts or for a specific currency.
- Perform currency exchange:
  - Transfer funds between accounts using the corresponding exchange rate by specifying the currencies for exchange.

---

### 3. Administrator Functionality
- Change exchange rates for currencies.
- Block and unblock users.
- Add new users and manage existing users.
- View user transactions.
- Assign administrative roles to other users.
- Manage user accounts (add, delete, update).

---

### 4. Data Organization and Validation
- Manage user data, accounts, and transaction ledgers.
- Implement validation for:
  - Email format.
  - Password strength (minimum length, includes letters and numbers).
- Utilize `Map` collections to organize data:
  - Users and their accounts.
  - Exchange rates by currency codes.
  - Multilingual support using language maps for UI messages.

---

### 5. Testing
- Develop JUnit tests to ensure the reliability of core functionalities.
- Focus on the Service layer for rigorous testing of business logic.

---

### 6. Optional Features
- Maintain a history of exchange rate changes.
- Implement custom exceptions and proper handling:
  - Inform users of errors and provide options to retry.

---

## Technologies and Tools
- **Programming Language:** Java
- **Data Storage:** In-memory structures or flat files
- **Testing Framework:** JUnit

## Project Repository
[GitHub Repository: ExchangeS](https://github.com/Kitasurfer/ExchangeS)

# Проект "Обменный пункт валют"

**Описание проекта**  
"Обменный пункт валют" — консольное приложение, имитирующее работу обменного пункта. Приложение позволяет пользователям управлять своими счетами в разных валютах, осуществлять обмен валют, пополнять и снимать средства, а также просматривать историю транзакций. Оно построено на многослойной архитектуре с четким разделением слоев Model, Service и Repository. Приложение предоставляет функциональность как для пользователей, так и для администраторов, что обеспечивает реалистичную и комплексную симуляцию работы обменного пункта валют.

---

## Технические особенности

### 1. Многослойная архитектура
- **Model Layer (Модель):**
  - Содержит сущности, такие как `User`, `Account`, `Transaction`, `Currency`, `ExchangeRate`, `Role`.
- **Service Layer (Сервис):**
  - Реализует бизнес-логику для управления аккаунтами, выполнения транзакций и проверки данных.
- **Repository Layer (Репозиторий):**
  - Отвечает за хранение и извлечение данных (например, в памяти или файлах).

---

### 2. Функционал пользователя
- Регистрация нового пользователя с проверкой email и пароля.
- Вход в аккаунт.
- Просмотр баланса (по всем счетам или по отдельному счету).
- Пополнение счета в выбранной валюте:
  - Если счета в данной валюте не существует, открыть новый.
- Снятие средств со счета:
  - Проверка возможности выполнения операции.
- Открытие нового счета.
- Закрытие счета:
  - Проверка наличия средств на счете перед закрытием.
- Просмотр истории транзакций:
  - По всем счетам или по конкретной валюте.
- Обмен валют:
  - Перевод средств между счетами с учетом обменного курса, указывая валюты для обмена.

---

### 3. Функционал администратора
- Изменение курсов валют.
- Блокировка и разблокировка пользователей.
- Добавление новых пользователей и управление существующими.
- Просмотр транзакций пользователей.
- Назначение других пользователей администраторами.
- Управление счетами пользователей (добавление, удаление, обновление).

---

### 4. Организация данных и валидация
- Управление данными пользователей, счетами и историей транзакций.
- Реализация валидации:
  - Формат email.
  - Надежность пароля (минимальная длина, наличие букв и цифр).
- Использование коллекций `Map` для организации данных:
  - Пользователи и их счета.
  - Курсы валют по кодам валют.
  - Поддержка многоязычности с использованием языковых мапов для сообщений интерфейса.

---

### 5. Тестирование
- Разработка JUnit тестов для проверки основных функций приложения.
- Особое внимание к тестированию слоя Service.

---

### 6. Дополнительные возможности
- История изменения курсов валют.
- Реализация пользовательских исключений и их обработка:
  - Уведомление пользователей об ошибках с предложением повторить ввод.

---

## Технологии и инструменты
- **Язык программирования:** Java
- **Хранение данных:** Структуры в памяти или файлы
- **Фреймворк для тестирования:** JUnit

## Репозиторий проекта
[GitHub Repository: ExchangeS](https://github.com/Kitasurfer/ExchangeS)
