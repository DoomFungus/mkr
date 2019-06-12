package edu.kpi.java.mkr.model;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private int userId;
    private String userName;
    private String userPassword;
    private String refreshToken;
    private Region region;
    private List<String> roles;
}
