package me.pabloestrada.exercise.core.exercise;

import org.bson.types.ObjectId;

public final class ExerciseCredentials {
    private ObjectId id;
    private String accessToken;
    private String refreshToken;

    public ExerciseCredentials() {
    }

    public ExerciseCredentials(final String accessToken,final String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.id = new ObjectId();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public boolean isValid() {
        return accessToken != null && refreshToken != null;
    }

    @Override
    public String toString() {
        return "ExerciseCredentials{" +
                "id=" + id +
                ", accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }
}
