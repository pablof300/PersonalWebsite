package me.pabloestrada.api.rest;

import com.google.inject.Inject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import me.pabloestrada.api.AuthenticationService;
import me.pabloestrada.api.ResponseAuthenticator;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("/auth")
@Api(value = "/auth")
public final class AuthenticationServiceRestMethods
{
    private final static String INVALID_REQUEST = "";

    private AuthenticationService delegate;

    @Inject
    public AuthenticationServiceRestMethods(final AuthenticationService delegate) {
        this.delegate = delegate;
    }

    @GET
    @ApiOperation(value = "Sign a new JWT token with username and password information")
    @Path("/sign")
    public String getJWT(@ApiParam(required = true) @QueryParam("username") final String username,
                         @ApiParam(required = true) @QueryParam("password") final String password,
                         @Context final HttpServletResponse response) {
        return delegate
                .signJWT(username, password)
                .orElseGet(() -> {
                    ResponseAuthenticator.sendError(Response.Status.UNAUTHORIZED, "Invalid password or user", response);
                    return INVALID_REQUEST;
                });
    }

    @GET
    @ApiOperation(value = "Verify a JWT token")
    @Path("/verify")
    public Boolean verifyJWT(@ApiParam(required = true) @QueryParam("token") final String token, @Context final HttpServletResponse response) {
        if (token == null) {
            ResponseAuthenticator.sendError(Response.Status.BAD_REQUEST,"Invalid parameters (missing token)", response);
            return false;
        }
        return delegate.verifyJWT(token);
    }
}
