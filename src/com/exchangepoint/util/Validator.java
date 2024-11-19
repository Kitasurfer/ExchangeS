package com.exchangepoint.util;


import com.exchangepoint.exception.ValidationException;

public class Validator {

    // Метод для валидации email
    public static void validateEmail(String email) throws ValidationException {
        // 1. Должна присутствовать одна и только одна собака (@)
        int indexAt = email.indexOf('@');
        if (indexAt == -1 || indexAt != email.lastIndexOf('@')) {
            throw new ValidationException("Email должен содержать один символ '@'.");
        }

        // 2. Точка должна идти после '@'
        int dotIndexAfterAt = email.indexOf('.', indexAt + 1);
        if (dotIndexAfterAt == -1) {
            throw new ValidationException("Email должен содержать '.' после '@'.");
        }

        // 3. После последней точки должно быть минимум два символа
        int lastDotIndex = email.lastIndexOf('.');
        if (lastDotIndex + 2 >= email.length()) {
            throw new ValidationException("Email должен содержать минимум два символа после последней точки.");
        }

        // 4. Проверка на недопустимые символы в email
        for (int i = 0; i < email.length(); i++) {
            char ch = email.charAt(i);
            boolean isValidChar = (Character.isAlphabetic(ch) ||
                    Character.isDigit(ch) ||
                    ch == '-' ||
                    ch == '_' ||
                    ch == '.' ||
                    ch == '@');
            if (!isValidChar) {
                throw new ValidationException("Email содержит недопустимые символы.");
            }
        }

        // 5. Символ '@' не может быть первым
        if (indexAt == 0) {
            throw new ValidationException("Символ '@' не может быть первым в email.");
        }

        // 6. Первый символ email должен быть буквой
        char firstChar = email.charAt(0);
        if (!Character.isAlphabetic(firstChar)) {
            throw new ValidationException("Первый символ email должен быть буквой.");
        }
    }

    // Метод для валидации пароля
    public static void validatePassword(String password) throws ValidationException {
        // 1. Пароль должен быть не менее 8 символов
        if (password.length() < 8) {
            throw new ValidationException("Пароль должен быть не менее 8 символов.");
        }

        // 2. Переменные для проверки условий пароля
        boolean hasLetter = false;
        boolean hasDigit = false;
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasSpecialSymbol = false;

        // 3. Определяем набор специальных символов
        String specialSymbols = "!%$@&*()[].,-";

        // 4. Перебираем символы пароля и проверяем их на соответствие требованиям
        for (char ch : password.toCharArray()) {
            if (Character.isLetter(ch)) {
                hasLetter = true;
                if (Character.isUpperCase(ch)) hasUpperCase = true;
                if (Character.isLowerCase(ch)) hasLowerCase = true;
            }
            if (Character.isDigit(ch)) {
                hasDigit = true;
            }
            if (specialSymbols.indexOf(ch) >= 0) {
                hasSpecialSymbol = true;
            }
        }

        // 5. Если пароль не удовлетворяет хотя бы одному требованию, выбрасываем исключение
        StringBuilder errorMessage = new StringBuilder("Пароль должен содержать:");
        int startLen = errorMessage.length();

        if (!hasLetter) errorMessage.append(" буквы");
        if (!hasDigit) errorMessage.append(" цифры");
        if (!hasUpperCase) errorMessage.append(" заглавные буквы");
        if (!hasLowerCase) errorMessage.append(" строчные буквы");
        if (!hasSpecialSymbol) errorMessage.append(" специальные символы (!%$@&*()[].,-)");

        if (errorMessage.length() > startLen) { // Если ошибка была добавлена
            throw new ValidationException(errorMessage.toString());
        }
    }
}
