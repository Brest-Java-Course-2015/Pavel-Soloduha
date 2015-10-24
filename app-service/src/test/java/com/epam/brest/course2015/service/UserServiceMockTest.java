package com.epam.brest.course2015.service;

import com.epam.brest.course2015.dao.UserDao;
import com.epam.brest.course2015.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.easymock.EasyMock.*;

/**
 * Created by pavel on 24.10.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-service-mock.xml"})
public class UserServiceMockTest {

    private static final Logger LOGGER = LogManager.getLogger();

    public static final int USER_ID = 1;
    public static final String USER_LOGIN_1 = "userLogin1";
    public static final String USER_PASSWORD_1 = "userPassword1";

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao mockUserDao;

    private static final User user = new User("userLogin3", "userPassword3");
    public static final User NEW_USER = new User(USER_ID, USER_LOGIN_1, USER_PASSWORD_1);


    @After
    public void clean() {
        verify(mockUserDao);
        reset(mockUserDao);
    }

    @Test
    public void testLogUser() {
        replay(mockUserDao);
        userService.logUser(user);
    }

    @Test
    public void testGetUserByLogin() {
        expect(mockUserDao.getUserByLogin(user.getLogin())).andReturn(user);
        replay(mockUserDao);
        User result = userService.getUserByLogin(user.getLogin());
        Assert.assertTrue(result == user);
    }

    @Test
    public void testAddUser() {
        expect(mockUserDao.getCountUsers(user.getLogin())).andReturn(-10);
        expect(mockUserDao.addUser(new User(user.getLogin(), ""))).andReturn(5);
        replay(mockUserDao);
        int id = userService.addUser(user);
        Assert.assertTrue(id == 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddExistUser() {
        expect(mockUserDao.getCountUsers(user.getLogin())).andReturn(1);
        replay(mockUserDao);
        userService.addUser(user);
    }

    @Test
    public void testGetUserById() {
        expect(mockUserDao.getUserById(NEW_USER.getUserId())).andReturn(NEW_USER);
        replay(mockUserDao);
        User result = userService.getUserById(NEW_USER.getUserId());
        Assert.assertTrue(result.equals(NEW_USER));
    }

    @Test
    public void testGetCountUsers() {
        expect(mockUserDao.getCountUsers(user.getLogin())).andReturn(1);
        replay(mockUserDao);
        Integer count = userService.getCountUsers(user.getLogin());
        Assert.assertTrue(count == 1);
    }
}