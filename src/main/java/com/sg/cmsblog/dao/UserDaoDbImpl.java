/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cmsblog.dao;

import com.sg.cmsblog.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author chris
 */
public class UserDaoDbImpl implements UserDao {
    
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private static final String SQL_INSERT_USER
            = "INSERT INTO User (Username, `Password`, Enabled, FirstName, LastName) "
            + "VALUES (?, ?, 1, ?, ?)";
    
    private static final String SQL_INSERT_AUTHORITY
            = "INSERT INTO Authority (Username, Authority) "
            + "VALUES (?, ?)";
    
    private static final String SQL_DELETE_USER
            = "DELETE FROM User WHERE Username = ?";
    
    private static final String SQL_DELETE_AUTHORITIES
            = "DELETE FROM Authority WHERE Username = ?";
    
    private static final String SQL_SELECT_USER
            = "SELECT * FROM User WHERE Username = ?";
    
    private static final String SQL_SELECT_USER_BY_ID
            = "SELECT * FROM User WHERE UserID = ?";
    
    private static final String SQL_SELECT_USERS_LIMIT_BY_NUM
            = "SELECT * FROM User "
            + "LIMIT ?";
    
    private static final String SQL_SELECT_ALL_USERS
            = "SELECT * FROM User";
    
    private static final String SQL_SELECT_AUTHORITIES
            = "SELECT * FROM Authority WHERE Username = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public User addUser(User user) {
        jdbcTemplate.update(SQL_INSERT_USER, 
                            user.getUsername(), 
                            user.getPassword(),
                            user.getFirstName(),
                            user.getLastName());

        int id = 
                jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", 
                                             Integer.class);

        user.setId(id);
        
        // now update the authority table
        insertAuthority(user);
        
        return user;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteUser(String username) {
        // delete all authorities for this user
        jdbcTemplate.update(SQL_DELETE_AUTHORITIES, username);
        // delete the user
        jdbcTemplate.update(SQL_DELETE_USER, username);
    }

    @Override
    public User getUser(String username) {
        try {
            // get the properties from the user table
            User user = jdbcTemplate.queryForObject(SQL_SELECT_USER, 
                                                    new UserMapper(), 
                                                    username);
            // get the authorities for this user and set list on the user
            user.setAuthorities(findAuthoritiesForUser(user));
        
            return user;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public User getUserById(int id) {
        try {
            // get the properties from the user table
            User user = jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_ID, 
                                                    new UserMapper(), 
                                                    id);
            // get the authorities for this user and set list on the user
            user.setAuthorities(findAuthoritiesForUser(user));
        
            return user;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<User> getUsersLimitByNum(int num) {
        return jdbcTemplate.query(SQL_SELECT_USERS_LIMIT_BY_NUM, 
                                  new UserMapper(),
                                  num);
    }

    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query(SQL_SELECT_ALL_USERS, new UserMapper());
    }
    
    // Helper Methods
    private void insertAuthority(User user) {
        final String username = user.getUsername();
        final List<String> authorities = user.getAuthorities();

        // insert user's roles
        for (String currentAuthority : authorities) {
            jdbcTemplate.update(SQL_INSERT_AUTHORITY, 
                                username, 
                                currentAuthority);
        }
    }
    
    private List<String> findAuthoritiesForUser(User user) {
        return jdbcTemplate.query(SQL_SELECT_AUTHORITIES, 
                                  new AuthorityMapper(), 
                                  user.getUsername());
    }

    private static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("UserID"));
            user.setUsername(rs.getString("Username"));
            user.setPassword(rs.getString("Password"));
            user.setFirstName(rs.getString("FirstName"));
            user.setLastName(rs.getString("LastName"));
            return user;
        }
    }
    
    private static final class AuthorityMapper implements RowMapper<String> {

        @Override
        public String mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getString("Authority");
        }
    }
}
