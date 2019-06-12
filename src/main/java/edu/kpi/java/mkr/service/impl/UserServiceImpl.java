package edu.kpi.java.mkr.service.impl;

import edu.kpi.java.mkr.DAO.UserDAO;
import edu.kpi.java.mkr.model.User;
import edu.kpi.java.mkr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> findAllUsers(Number limit, Number offset) {
        return userDAO.findAllUsers(limit, offset);
    }

    @Override
    public User findUser(Number userId) {
        return userDAO.findUser(userId);
    }
}