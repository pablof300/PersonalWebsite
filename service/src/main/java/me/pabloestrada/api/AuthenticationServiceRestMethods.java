package me.pabloestrada.api;

import com.google.inject.Inject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.IOException;

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
    public String getJWT(@QueryParam("username") final String username, @QueryParam("password") final String password,
                         @Context final HttpServletResponse response) {
        if (username == null || password == null) {
            return sendError(Response.Status.BAD_REQUEST,"Invalid parameters (missing username or password)", response);
        }
        return delegate
                .signJWT(username, password)
                .orElseGet(() -> sendError(Response.Status.UNAUTHORIZED, "Invalid password or user", response));
    }

    @GET
    @ApiOperation(value = "Verify a JWT token")
    @Path("/verify")
    public boolean verifyJWT(@QueryParam("token") final String token, @Context final HttpServletResponse response) {
        if (token == null) {
            sendError(Response.Status.BAD_REQUEST,"Invalid parameters (missing token)", response);
            return false;
        }
        return delegate.verifyJWT(token);
    }

    private String sendError(final Response.Status status, final String message, final HttpServletResponse response) {
        try {
            final String jsonError = "{ error: '" + message + "' }";
            response.setContentType("application/json");
            response.setStatus(status.getStatusCode());
            response.getOutputStream().write(jsonError.getBytes());
            response.getOutputStream().close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return INVALID_REQUEST;
    }
}
