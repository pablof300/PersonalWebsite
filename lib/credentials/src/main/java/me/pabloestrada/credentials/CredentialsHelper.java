package me.pabloestrada.credentials;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public final class CredentialsHelper {

    private final String credentialsPath;
    private Map<String, String> entityToCredential;

    // TODO:
    // Encrypt credentials
    public CredentialsHelper() {
        this.entityToCredential = new HashMap<>();
        final File serviceFolderPath = new File(
                CredentialsHelper.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParentFile().getParentFile();
        this.credentialsPath = serviceFolderPath.getAbsolutePath() + "/.credentials";
        try (Stream<String> stream = Files.lines(Paths.get(credentialsPath))) {
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
        if (!entityToCredential.containsKey(entity)) {
            return Optional.empty();
        }
        return Optional.of(entityToCredential.get(entity));
    }

    public void putCredential(final String entity, final String credential) {
        entityToCredential.put(entity, credential);
        replaceSelected(entity, credential);
    }

    private void replaceSelected(String entity, String credential) {
        try {
            final BufferedReader file = new BufferedReader(new FileReader(credentialsPath));
            final StringBuffer inputBuffer = new StringBuffer();
            boolean foundEntity = false;
            String line;

            while ((line = file.readLine()) != null) {
                if (line.contains(entity)) {
                    line = entity + "=" + credential;
                    foundEntity = true;
                }
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }

            if (!foundEntity) {
                inputBuffer.append(entity + "=" + credential);
                inputBuffer.append('\n');
            }

            file.close();
            final FileOutputStream fileOut = new FileOutputStream(credentialsPath);
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();

        } catch (final Exception e) {
            System.out.println("Problem reading file.");
        }
    }
}
