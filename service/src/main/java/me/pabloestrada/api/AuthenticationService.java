package me.pabloestrada.api;

import java.util.Optional;

public abstract class AuthenticationService
{
    public abstract Optional<String> signJWT(final String username, final String password);

    public abstract boolean verifyJWT(final String token);
}
