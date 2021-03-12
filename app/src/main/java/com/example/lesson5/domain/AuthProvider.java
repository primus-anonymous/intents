package com.example.lesson5.domain;

public class AuthProvider {

    public static final AuthProvider INSTANCE = new AuthProvider();

    private boolean isAuthorized = false;

    public boolean isAuthorized() {
        return isAuthorized;
    }

    public void setAuthorized(boolean authorized) {
        isAuthorized = authorized;
    }
}
