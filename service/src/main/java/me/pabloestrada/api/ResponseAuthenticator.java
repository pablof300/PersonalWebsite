package me.pabloestrada.api;

import com.google.inject.Inject;
import me.pabloestrada.core.authentication.UserAuthenticator;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.function.Supplier;

public final class ResponseAuthenticator {

    private UserAuthenticator authenticator;

    @Inject
    public ResponseAuthenticator(final UserAuthenticator userAuthenticator) {
        this.authenticator = userAuthenticator;
    }

    public <T> T authenticateAndCatchErrors(final Supplier<T> data, final String token, final HttpServletResponse response) {
        return authenticate(token, response) ? data.get() : null;
    }

    public boolean authenticate(final String token, final HttpServletResponse response) {
        if (token == null) {
            sendError(Response.Status.BAD_REQUEST, "Invalid parameters (missing token)", response);
            return false;
        }
        if (!authenticator.verifyJWT(token).isPresent()) {
            sendError(Response.Status.UNAUTHORIZED, "Invalid token", response);
            return false;
        }
        return true;
    }

    public void authenticate(final String token, final HttpServletResponse response, final Runnable ifSuccessful) {
        if (authenticate(token, response)) {
            ifSuccessful.run();
        }
    }

    public static void sendError(final Response.Status status, final String message, final HttpServletResponse response) {
        try {
            final String jsonError = "{ error: '" + message + "' }";
            response.setContentType("application/json");
            response.setStatus(status.getStatusCode());
            response.getOutputStream().write(jsonError.getBytes());
            response.getOutputStream().close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
