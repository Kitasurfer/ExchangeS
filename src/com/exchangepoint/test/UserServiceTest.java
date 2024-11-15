/*
package com.exchangepoint.test;

*/
/**
 * Group: 52-1, "AIT Hi-tech team" GMBH
 * Author: Bogdan Fesenko
 * Date: 15-11-2024
 *//*

*/
/*

 *//*



import com.exchangepoint.service.UserService;
import com.exchangepoint.service.impl.UserServiceImpl;
import com.exchangepoint.repository.UserRepository;
import com.exchangepoint.repository.impl.UserRepositoryImpl;
import com.exchangepoint.model.User;
import com.exchangepoint.exception.ValidationException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserServiceTest {
    private UserService userService;

    @Before
    public void setup() {
        UserRepository userRepository = new UserRepositoryImpl();
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void testRegisterValidUser() throws ValidationException {
        User user = new User(0, "Ivan", "ivan@example.com", "password123", null, false);
        userService.register(user);
        assertNotNull(user.getId());
    }

    @Test(expected = ValidationException.class)
    public void testRegisterInvalidEmail() throws ValidationException {
        User user = new User(0, "Ivan", "invalid-email", "password123", null, false);
        userService.register(user);
    }
}
*/
