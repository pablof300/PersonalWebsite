package me.pabloestrada.core.authentication;

import com.google.inject.Inject;
import io.jsonwebtoken.*;
import me.pabloestrada.core.user.UserDAO;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

public final class UserAuthenticator
{
    private final static int EXPIRATION_TIME_IN_DAYS = 1;
    private final static String issuer = "https://pabloestrada.me/";
    private final static String key = "9D3C30CA5665C28AE9D7CA874BBF90B5818E81265B2808898FB85F1CA08136D4";

    private final UserDAO userDAO;

    @Inject
    public UserAuthenticator(final UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Optional<String> signJWT(final String username, final String password) {
        if (!userDAO.verifyCredentials(username, password)) {
            return Optional.empty();
        }
        return Optional.of(Jwts.builder()
                .setIssuer(issuer)
                .setSubject(username)
                .setExpiration(asDate(LocalDate.now().plusDays(EXPIRATION_TIME_IN_DAYS)))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact());
    }

    public Optional<String> verifyJWT(final String jwt) {
        try {
            final String username = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(jwt)
                    .getBody()
                    .getSubject();
            return Optional.of(username);
        } catch (final JwtException ex) {
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    private Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }
}
