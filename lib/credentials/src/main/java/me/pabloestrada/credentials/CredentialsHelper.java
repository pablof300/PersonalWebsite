package me.pabloestrada.credentials;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public final class CredentialsHelper {

    private static final String CREDENTIALS_PATH = "/Users/pabloestrada/Desktop/PersonalWebsite/.credentials";

    private Map<String, String> entityToCredential;

    // TODO:
    // Encrypt credentials
    // Get rid of absolute path
    public CredentialsHelper() {
        this.entityToCredential = new HashMap<>();
        try (Stream<String> stream = Files.lines(Paths.get(CREDENTIALS_PATH))) {
            stream.forEach(line -> {
                final int delimiter = line.indexOf('=');
                if (line.length() > 3 && delimiter != -1)
                    entityToCredential.put(line.substring(0, delimiter), line.substring(delimiter + 1));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Optional<String> getCredential(final String entity) {
        return Optional.of(entityToCredential.get(entity));
    }
}
