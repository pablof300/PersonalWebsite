package me.pabloestrada.core;

import me.pabloestrada.core.user.User;
import me.pabloestrada.core.user.UserDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

final class UserDAOTest
{
    private final static String BASE_USERNAME = "Pablo";
    private final static String PASSWORD = "123456";

    private static UserDAO userDAO;
    private static User user;

    @BeforeAll
    static void setUp(){
        user = new User(BASE_USERNAME, PASSWORD, true);
        userDAO = new UserDAO(null);
        userDAO.insertUser(user);
    }

    @DisplayName("Testing adding a new user")
    @Test
    void testAddingSingleUser() {
        final String newUsername = BASE_USERNAME + System.currentTimeMillis();
        final User newUser = new User(newUsername, PASSWORD, true);
        userDAO.insertUser(newUser);
        Assertions.assertEquals(newUser, userDAO.getUser(newUsername).get());
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

    @DisplayName("Testing verification of credentials with correct password")
    @Test
    void testVerificationOfCredentialsWithCorrectPassword() {
        Assertions.assertTrue(userDAO.verifyCredentials(BASE_USERNAME, PASSWORD));
    }

    @DisplayName("Testing verification of credentials with incorrect password")
    @Test
    void testVerificationOfCredentialsWithIncorrectPassword() {
        Assertions.assertFalse(userDAO.verifyCredentials(BASE_USERNAME, PASSWORD + "!"));
    }
}
