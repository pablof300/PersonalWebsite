package me.pabloestrada.core;

import me.pabloestrada.core.user.User;
import me.pabloestrada.core.user.UserDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserDAOTest {

    private static UserDAO userDAO;

    @BeforeAll
    static void setUp(){
        userDAO = new UserDAO();
    }

    @DisplayName("Testing adding a new user is successful")
    @Test
    void testAddingSingleUser() {
        final User user = new User("Pablo", "123456");
        userDAO.insertUser(user.getUsername(), user.getPassword());
        Assertions.assertEquals(userDAO.getUser("Pablo").get(), user);
    }
}
