/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cmsblog.dao;

import com.sg.cmsblog.model.User;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author chris
 */
public class UserDaoTest {
    
    UserDao dao;
    
    public UserDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("userDao", UserDao.class);
        
        // remove all of the users
        List<User> users = dao.getAllUsers();
        for (User currentUser : users) {
            dao.deleteUser(currentUser.getUsername());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addUser method, of class UserDao.
     */
    @Test
    public void testAddGetUser() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");

        dao.addUser(user);

        User fromDao = dao.getUser(user.getUsername());
        assertEquals(user, fromDao);
    }

    /**
     * Test of deleteUser method, of class UserDao.
     */
    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");

        dao.addUser(user);

        User fromDao = dao.getUser(user.getUsername());
        assertEquals(user, fromDao);
        dao.deleteUser(user.getUsername());
        assertNull(dao.getUser(user.getUsername()));
    }

    /**
     * Test of getUserById method, of class UserDao.
     */
    @Test
    public void testGetUserById() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.addAuthority("ROLE_USER");
        user.addAuthority("ROLE_ADMIN");

        dao.addUser(user);

        User fromDao = dao.getUserById(user.getId());
        assertEquals(user, fromDao);
    }

    /**
     * Test of getUsersLimitByNum method, of class UserDao.
     */
    @Test
    public void testGetUsersLimitByNum() {
        User user1 = new User();
        user1.setUsername("admin");
        user1.setPassword("password");
        user1.setFirstName("John");
        user1.setLastName("Smith");
        user1.addAuthority("ROLE_USER");
        user1.addAuthority("ROLE_ADMIN");
        
        dao.addUser(user1);
        
        User user2 = new User();
        user2.setUsername("user");
        user2.setPassword("password");
        user2.setFirstName("Jane");
        user2.setLastName("Doe");
        user2.addAuthority("ROLE_USER");
        
        dao.addUser(user2);
        
        assertEquals(1, dao.getUsersLimitByNum(1).size());
    }

    /**
     * Test of getAllUsers method, of class UserDao.
     */
    @Test
    public void testGetAllUsers() {
        User user1 = new User();
        user1.setUsername("admin");
        user1.setPassword("password");
        user1.setFirstName("John");
        user1.setLastName("Smith");
        
        dao.addUser(user1);
        
        User user2 = new User();
        user2.setUsername("user");
        user2.setPassword("password");
        user2.setFirstName("Jane");
        user2.setLastName("Doe");
        
        dao.addUser(user2);
        
        assertEquals(2, dao.getAllUsers().size());
    }
}
