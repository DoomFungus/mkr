package edu.kpi.java.mkr.service;

import edu.kpi.java.mkr.model.User;

import java.util.List;

public interface UserService {
    List<User> findAllUsers(Number limit, Number offset);

    User findUser(Number userId);
}
