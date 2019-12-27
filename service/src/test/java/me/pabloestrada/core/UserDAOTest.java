package me.pabloestrada.core;

import me.pabloestrada.core.user.User;
import me.pabloestrada.core.user.UserDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

final class UserDAOTest
{
    private final static String BASE_USERNAME = "Pablo";
    private final static String PASSWORD = "123456";

    private static UserDAO userDAO;

    @BeforeAll
    static void setUp(){
        userDAO = new UserDAO();
    }

    @DisplayName("Testing adding a new user")
    @Test
    void testAddingSingleUser() {
        final User user = new User(BASE_USERNAME, PASSWORD, true);
        userDAO.insertUser(user);
        Assertions.assertEquals(user, userDAO.getUser(BASE_USERNAME).get());
    }

    @DisplayName("Testing adding multiple same users")
    @Test
    void testAddingMultipleSameUsers() {
        final int NUMBER_OF_USERS = 10;
        final String randomUsername = BASE_USERNAME + System.currentTimeMillis();
        int usersAdded = 0;
        for (int i = 0; i < NUMBER_OF_USERS; i++) {
            if (userDAO.insertUser(new User(randomUsername, PASSWORD, true))) {
                usersAdded++;
            }
        }
        Assertions.assertEquals(1, usersAdded);
    }
}
