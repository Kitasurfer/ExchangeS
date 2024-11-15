package com.exchangepoint.service;

/**
 * Group: 52-1, "AIT Hi-tech team" GMBH
 * Author: Bogdan Fesenko
 * Date: 15-11-2024
 */
/*

 */



import com.exchangepoint.model.Currency;
import com.exchangepoint.exception.UserNotFoundException;

public interface AdminService {
    void setExchangeRate(Currency from, Currency to, double rate);
    void blockUser(long userId) throws UserNotFoundException;
    void unblockUser(long userId) throws UserNotFoundException;
}
