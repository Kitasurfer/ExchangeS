package com.exchangepoint.repository;


import com.exchangepoint.model.Db;
import com.exchangepoint.model.User;
import java.util.*;

public class UserRepositoryImpl implements UserRepository {

    private Db db;

    public UserRepositoryImpl(Db db) {
        this.db = db;
    }

    @Override
    public void save(User user) {
        Map<Long, User> users = db.getUsers();
        if (user.getId() == null) {
            long userIdSequence = users.keySet().stream().max(Long::compareTo).get();
            // set new generated id to user
            user.setId(++userIdSequence);
        }
        users.put(user.getId(), user);
    }

    @Override
    public Optional<User> findById(long id) {
        Map<Long, User> users = db.getUsers();
        return users.containsKey(id) ? Optional.of(users.get(id)) : Optional.empty();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Map<Long, User> users = db.getUsers();
        return users.values().stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    @Override
    public List<User> findAll() {
        Map<Long, User> users = db.getUsers();
        return new ArrayList<>(users.values());
    }
}
