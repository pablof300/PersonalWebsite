package me.pabloestrada.exercise.client;

final class AuthenticationToken {
    private String refresh_token;
    private String access_token;
    private long expires_at;

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public long getExpires_at() {
        return expires_at;
    }

    public void setExpires_at(long expires_at) {
        this.expires_at = expires_at;
    }

    @Override
    public String toString() {
        return "AuthenticationToken{" +
                "refresh_token='" + refresh_token + '\'' +
                ", access_token='" + access_token + '\'' +
                ", expires_at=" + expires_at +
                '}';
    }
}
