package com.exchangepoint.service;


import com.exchangepoint.model.Currency;
import com.exchangepoint.exception.UserNotFoundException;
import com.exchangepoint.model.User;

import java.util.List;

public interface AdminService {
    void setExchangeRate(Currency from, Currency to, double rate);
    void blockUser(long userId) throws UserNotFoundException;
    void unblockUser(long userId) throws UserNotFoundException;
    List<User> getUsers();
}
