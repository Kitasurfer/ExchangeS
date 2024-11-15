package com.exchangepoint.util;

/**
 * Group: 52-1, "AIT Hi-tech team" GMBH
 * Author: Bogdan Fesenko
 * Date: 15-11-2024
 */
/*

 */


import com.exchangepoint.exception.ValidationException;

public class Validator {
    public void validateEmail(String email) throws ValidationException {
        int indexAt = email.indexOf('@');
        if (indexAt == -1 || indexAt != email.lastIndexOf('@')) {
            throw new ValidationException("Email должен содержать один символ '@'.");
        }
        int dotIndexAfterAt = email.indexOf('.', indexAt + 1);
        if (dotIndexAfterAt == -1) {
            throw new ValidationException("Email должен содержать '.' после '@'.");
        }
        int lastDotIndex = email.lastIndexOf('.');
        if (lastDotIndex + 2 >= email.length()) {
            throw new ValidationException("Email должен содержать минимум два символа после последней точки.");
        }
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
        if (indexAt == 0) {
            throw new ValidationException("Символ '@' не может быть первым в email.");
        }
        char firstChar = email.charAt(0);
        if (!Character.isAlphabetic(firstChar)) {
            throw new ValidationException("Первый символ email должен быть буквой.");
        }

    }


    public void validatePassword(String password) throws ValidationException {
        if (password.length() < 8) {
            throw new ValidationException("Пароль должен быть не менее 8 символов.");
        }
        boolean hasLetter = false;
        boolean hasDigit = false;
        for (char ch : password.toCharArray()) {
            if (Character.isLetter(ch)) hasLetter = true;
            if (Character.isDigit(ch)) hasDigit = true;
        }
        if (!hasLetter || !hasDigit) {
            throw new ValidationException("Пароль должен содержать буквы и цифры.");
        }
    }

}

