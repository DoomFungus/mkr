package edu.kpi.java.mkr.DAO.mapper;

import edu.kpi.java.mkr.model.Region;
import edu.kpi.java.mkr.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class UserWithPasswordMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User u = new User();
        u.setUserId(resultSet.getInt(1));
        u.setUsername(resultSet.getString(2));
        u.setPassword(resultSet.getString(3));
        u.setRoles(new LinkedList<>());
        return u;
    }
}
