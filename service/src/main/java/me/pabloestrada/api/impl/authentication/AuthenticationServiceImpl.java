package me.pabloestrada.api.impl.authentication;

import com.google.inject.Inject;
import me.pabloestrada.api.AuthenticationService;
import me.pabloestrada.core.authentication.UserAuthenticator;

final public class AuthenticationServiceImpl
    extends AuthenticationService
{
    private UserAuthenticator userAuthenticator;

    @Inject
    public AuthenticationServiceImpl(final UserAuthenticator userAuthenticator) {
        this.userAuthenticator = userAuthenticator;
    }

    @Override
    public String signJWT(String username, String password) {
        // Check if username is empty or password
        return userAuthenticator.signJWT(username, password);
    }
}
