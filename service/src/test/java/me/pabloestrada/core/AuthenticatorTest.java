package me.pabloestrada.core;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import me.pabloestrada.core.authentication.UserAuthenticator;
import me.pabloestrada.core.user.User;
import me.pabloestrada.core.user.UserDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

final class AuthenticatorTest
{
    private final static String BASE_USERNAME = "Pablo";
    private final static String PASSWORD = "123456";

    private static UserAuthenticator userAuthenticator;

    @BeforeAll
    static void setUp(){
        userAuthenticator = new UserAuthenticator(new UserDAO());
    }


    @DisplayName("Testing signing and verifying a JWT token given a username and password")
    @Test
    void testGenerationOfJWT() {
        final String token = userAuthenticator.signJWT(BASE_USERNAME, PASSWORD).get();
        userAuthenticator.verifyJWT(token);
        Assertions.assertEquals(Optional.of(BASE_USERNAME), userAuthenticator.verifyJWT(token));
    }
}
