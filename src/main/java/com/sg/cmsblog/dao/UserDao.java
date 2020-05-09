/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cmsblog.dao;

import com.sg.cmsblog.model.User;
import java.util.List;

/**
 *
 * @author chris
 */
public interface UserDao {
    
    public User addUser(User user);
    
    public void deleteUser(String username);
    
    public User getUser(String username);
    
    public User getUserById(int id);
    
    public List<User> getUsersLimitByNum(int num);
    
    public List<User> getAllUsers();
}
