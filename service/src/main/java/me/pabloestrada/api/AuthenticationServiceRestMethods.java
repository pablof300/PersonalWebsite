package me.pabloestrada.api;

import com.google.inject.Inject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.pabloestrada.core.authentication.UserAuthenticator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/auth")
@Api(value = "/auth")
public class AuthenticationServiceRestMethods {
    private AuthenticationService delegate;
    private UserAuthenticator userAuthenticator;

    @Inject
    public AuthenticationServiceRestMethods(final AuthenticationService delegate, final UserAuthenticator userAuthenticator) {
        this.delegate = delegate;
        this.userAuthenticator = userAuthenticator;
    }

    @GET
    @ApiOperation(value = "Sign a new JWT token with username and password information")
    @Path("/sign")
    public String getJWT(@QueryParam("username") final String username, @QueryParam("password") final String password) {
        return delegate.signJWT(username, password);
    }
}
