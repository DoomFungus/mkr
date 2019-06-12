package edu.kpi.java.mkr.service;

public interface AuthService {
    String signIn(String username, String password);

    String signUp(String username, String password);
}
