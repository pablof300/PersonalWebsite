package me.pabloestrada.core;

import java.util.Optional;

public final class DatabaseConstants
{
    public final static String DATABASE_NAME = "persian";
    public final static String USER_COLLECTION_NAME = "users";
    public final static String BASE_WEBSITE_INFO_COLLECTION_NAME = "website";
    public final static String PROJECT_INFO_COLLECTION_NAME = "projects";
    private static Optional<String> MONGO_CONNECTION_STRING;

    public static String getMongoConnectionString() {
        return MONGO_CONNECTION_STRING.orElseThrow();
    }

    public static void setMongoConnectionString(final String mongoConnectionString) {
        MONGO_CONNECTION_STRING = Optional.of(mongoConnectionString);
    }
}
