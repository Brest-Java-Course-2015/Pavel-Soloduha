package com.epam.brest.course2015.service;

import com.epam.brest.course2015.dao.UserDao;
import com.epam.brest.course2015.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by pavel on 15.10.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-service.xml"})
@Transactional()
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void testGetAllUsers() throws Exception {
        List<User> users = userService.getAllUsers();
        assertTrue(users.size() == 2);
    }

    @Test(expected = NullPointerException.class)
    public void testAddNullUser() throws Exception {
        userService.addUser(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddUserWithNotNullId() throws Exception {
        User user = new User();
        user.setUserId(1);
        userService.addUser(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddUserWithEmptyLogin() throws Exception {
        User user = new User();
        user.setLogin("");
        userService.addUser(user);
    }

    @Test
    public void testGetUserById() {
        User user = userService.getUserById(1);
        assertTrue(user.getLogin().equals("userLogin1"));
    }

    @Test
    public void testGetUserByLogin() {
        User user = userService.getUserByLogin("userLogin1");
        assertTrue(user.getPassword().equals("userPassword1"));
    }
}