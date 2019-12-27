package me.pabloestrada.api;

public abstract class AuthenticationService
{
    public abstract String signJWT(final String username, final String password);
}
