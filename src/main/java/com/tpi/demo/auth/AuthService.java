package com.tpi.demo.auth;

public interface AuthService {
    void register(User user);
    void login(User user);
    User getUserSession();
    void logoutUser();
}
