/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cmsblog.dao;

import com.sg.cmsblog.model.Tag;
import com.sg.cmsblog.model.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chris
 */
public class UserDaoStubImpl implements UserDao {
    
    private User onlyUser;
    private List<User> userList = new ArrayList<>();
    
    public UserDaoStubImpl() {
        onlyUser = new User();
        onlyUser.setId(1);
        onlyUser.setUsername("admin");
        onlyUser.setPassword("password");
        onlyUser.setFirstName("John");
        onlyUser.setLastName("Smith");
        onlyUser.addAuthority("ROLE_USER");
        onlyUser.addAuthority("ROLE_ADMIN");
        
        userList.add(onlyUser);
    }

    @Override
    public User addUser(User user) {
        if (user.getUsername().equals(onlyUser.getUsername())) {
            return onlyUser;
        } else {
            return null;
        }
    }

    @Override
    public void deleteUser(String username) {
        // do nothing...
    }

    @Override
    public User getUser(String username) {
        if (username.equals(onlyUser.getUsername())) {
            return onlyUser;
        } else {
            return null;
        }
    }

    @Override
    public User getUserById(int id) {
        if (id == onlyUser.getId()) {
            return onlyUser;
        } else {
            return null;
        }
    }

    @Override
    public List<User> getUsersLimitByNum(int num) {
        return userList;
    }

    @Override
    public List<User> getAllUsers() {
        return userList;
    }
}
