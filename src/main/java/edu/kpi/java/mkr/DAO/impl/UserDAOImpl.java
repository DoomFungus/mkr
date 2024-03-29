package edu.kpi.java.mkr.DAO.impl;

import edu.kpi.java.mkr.DAO.UserDAO;
import edu.kpi.java.mkr.DAO.mapper.UserMapper;
import edu.kpi.java.mkr.DAO.mapper.UserWithPasswordMapper;
import edu.kpi.java.mkr.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    public UserDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String GET_ALL_USERS = "SELECT u.user_id, u.user_name, r.region_id, r.region_name\n" +
            "FROM user_a u\n" +
            "JOIN region r on u.region_id = r.region_id\n" +
            "LIMIT ?\n" +
            "OFFSET ?";

    private static final String GET_USER_BY_ID = "SELECT u.user_id, u.user_name, r.region_id, r.region_name\n" +
            "FROM user_a u\n" +
            "JOIN region r on u.region_id = r.region_id\n" +
            "WHERE u.user_id = ?";

    private static final String GET_ROLES_FOR_USERS = "SELECT ar.user_id, ra.role_name\n" +
            "FROM assigned_role ar\n" +
            "JOIN role_a ra on ar.role_id = ra.role_id\n" +
            "WHERE ar.user_id IN (:ids)";

    private static final String GET_USER_BY_USERNAME = "SELECT u.user_id, u.user_name, u.user_password\n" +
            "FROM user_a u\n" +
            "WHERE u.user_name = ?";

    private static final String CREATE_USER = "INSERT INTO user_a(user_name, user_password, creation_date)" +
            "values (?, ?, ?)";

    private  static final String CREATE_USER_ROLE = "INSERT INTO assigned_role values ((SELECT user_id\n" +
            "            FROM user_a\n" +
            "            where user_name = ?), ?)";
    @Override
    public List<User> findAllUsers(Number limit, Number offset) {
        return attachRoles(jdbcTemplate
                .query(GET_ALL_USERS, new Object[]{limit, offset}, new UserMapper()));
    }

    @Override
    public User findUser(Number userId) {
        return attachRoles(jdbcTemplate
                .queryForObject(GET_USER_BY_ID, new Object[]{userId}, new UserMapper()));
    }


    @Override
    public User findUser(String userName) {
        return attachRoles(jdbcTemplate.queryForObject(GET_USER_BY_USERNAME, new Object[]{userName}, new UserWithPasswordMapper()));
    }

    @Override
    public void createUser(User user) {
        jdbcTemplate.update(CREATE_USER, new Object[]{user.getUsername(), user.getPassword(), LocalDate.now()});
        jdbcTemplate.update(CREATE_USER_ROLE, new Object[]{user.getUsername(), 1});
    }

    private List<User> attachRoles(List<User> users){
        Map<Integer, User> usersMap = users.stream()
                .collect(Collectors.toMap(User::getUserId, Function.identity()));
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("ids", usersMap.keySet());
        SqlRowSet result = namedParameterJdbcTemplate.queryForRowSet(GET_ROLES_FOR_USERS, parameters);
        while(result.next()){
            usersMap.get(result.getInt(1)).getRoles().add(result.getString(2));
        }
        return users;
    }

    private User attachRoles(User user){
        List<User> users = new ArrayList<User>();
        users.add(user);
        return attachRoles(users).get(0);
    }
}
