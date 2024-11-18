package com.exchangepoint.repository;

import com.exchangepoint.model.User;
import java.util.*;

public class UserRepositoryImpl implements UserRepository {

    private Map<Long, User> users = new HashMap<>();
    private long userIdSequence = 1;

    @Override
    public void save(User user) {
        user.setId(userIdSequence++);
        users.put(user.getId(), user);
    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return users.values().stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }
}
