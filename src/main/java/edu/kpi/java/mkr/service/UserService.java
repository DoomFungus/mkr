package edu.kpi.java.mkr.service;

import edu.kpi.java.mkr.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> findAllUsers(Number limit, Number offset);

    User findUser(Number userId);

    User findUser(String userName);

    User findUser();
}
