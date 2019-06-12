package edu.kpi.java.mkr.DAO.mapper;


import edu.kpi.java.mkr.model.Region;
import edu.kpi.java.mkr.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User u = new User();
        Region r = new Region();
        u.setUserId(resultSet.getInt(1));
        u.setUserName(resultSet.getString(2));
        r.setRegionId(resultSet.getInt(3));
        r.setRegionName(resultSet.getString(4));
        u.setRegion(r);
        u.setRoles(new LinkedList<>());
        return u;
    }
}
