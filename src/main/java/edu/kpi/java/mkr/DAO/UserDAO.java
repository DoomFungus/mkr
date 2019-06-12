package edu.kpi.java.mkr.DAO;

import edu.kpi.java.mkr.model.User;

import java.util.List;

public interface UserDAO {
    List<User> findAllUsers(Number limit, Number offset);

    User findUser(Number userId);

    User findUser(String userName);

    void createUser(User user);
}
