package com.exchangepoint.repository;

import com.exchangepoint.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    void save(Account account); // Сохранить или обновить аккаунт
    Optional<Account> findById(long id); // Найти аккаунт по ID
    Optional<List<Account>> findByUserId(Long userId); // Найти аккаунты пользователя
    void delete(Long id); // Удалить аккаунт по ID



}



