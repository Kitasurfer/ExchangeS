package com.exchangepoint.util;

import com.exchangepoint.exception.ValidationException;

public class ValidateApp {
    public static void main(String[] args) {
        Validator validator = new Validator();

        // Пример email и пароля
        String email = "test@example.com";
        String password = "Qwerty123!";

        try {
            // Валидация email
            validator.validateEmail(email);
            System.out.println("Email прошел проверку");

            // Валидация пароля
            validator.validatePassword(password);
            System.out.println("Пароль прошел проверку");

        } catch (ValidationException e) {
            // Ловим исключение и выводим сообщение об ошибке
            System.out.println("Ошибка валидации: " + e.getMessage());
        }
    }
}

