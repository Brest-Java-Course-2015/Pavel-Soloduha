package com.epam.brest.course2015.service;

import com.epam.brest.course2015.domain.User;
import com.epam.brest.course2015.dto.UserDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pavel on 15.10.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-service.xml"})
@Transactional()
public class UserServiceImplTest {

    private static final Logger LOGGER = LogManager.getLogger();

    public static final Integer USER_ID1 = 1;
    public static final String USER_LOGIN1 = "userLogin1";
    public static final String USER_PASSWORD1 = "userPassword1";

    @Autowired
    private UserService userService;

    private static final User user = new User("userLogin3", "userPassword3");

    @Test
    public void testGetAllUsers() throws Exception {
        LOGGER.debug("test: getAllUsers()");
        Assert.assertTrue(userService.getAllUsers().size() > 0);
    }

    @Test
    public void testAddUser() throws Exception {
        LOGGER.debug("test: addUser()");
        int sizeBefore = userService.getAllUsers().size();
        userService.addUser(user);
        Assert.assertTrue(sizeBefore + 1 == userService.getAllUsers().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullUser() throws Exception {
        LOGGER.debug("test: addNullUser()");
        userService.addUser(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddUserWithNotNullId() throws Exception {
        LOGGER.debug("test: addUserWithNotNullId()");
        User user = new User();
        user.setUserId(1);
        userService.addUser(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddUserWithNullLogin() throws Exception {
        LOGGER.debug("test: addUserWithNullLogin()");
        User user = new User();
        userService.addUser(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddUserWithNullPassword() throws Exception {
        LOGGER.debug("test: addUserWithNullPassword()");
        User user = new User();
        user.setLogin("login");
        userService.addUser(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddUserWithNotUniqueLogin() throws Exception {
        LOGGER.debug("test: addUserWithNotUniqueLogin()");
        User user = new User(USER_LOGIN1, USER_PASSWORD1);
        userService.addUser(user);
    }

    @Test
    public void testGetUserById() throws Exception {
        LOGGER.debug("test: getUserById()");
        User user = userService.getUserById(1);
        Assert.assertTrue(user.getLogin().equals("userLogin1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetUserByNullId() throws Exception {
        LOGGER.debug("test: getUserByNullId()");
        userService.getUserById(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetUserByNegativeId() throws Exception {
        LOGGER.debug("test: getUserByNegativeId()");
        userService.getUserById(-10);
    }

    @Test
    public void testGetUserByLogin() throws Exception {
        LOGGER.debug("test: getUserByLogin()");
        User user = userService.getUserByLogin("userLogin1");
        Assert.assertTrue(user.getLogin().equals("userLogin1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetUserByNullLogin() throws Exception {
        LOGGER.debug("test: getUserByNullLogin()");
        userService.getUserByLogin(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetUserByEmptyLogin() throws Exception {
        LOGGER.debug("test: getUserByNullLogin()");
        userService.getUserByLogin("");
    }

    @Test
    public void testUpdateUser() throws Exception {
        LOGGER.debug("test: updateUser()");
        User user = userService.getUserByLogin(USER_LOGIN1);
        user.setPassword(USER_PASSWORD1 + 12);
        userService.updateUser(user);
        User newUser = userService.getUserById(user.getUserId());
        Assert.assertTrue(user.getLogin().equals(newUser.getLogin()));
        Assert.assertTrue(user.getPassword().equals(newUser.getPassword()));
        Assert.assertTrue(user.getCreatedDate().equals(newUser.getCreatedDate()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateNullUser() throws Exception {
        LOGGER.debug("test: testUpdateNullUser()");
        userService.updateUser(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateUserNullId() throws Exception {
        LOGGER.debug("test: updateUserNullId()");
        User newUser = new User(USER_LOGIN1, USER_PASSWORD1);
        userService.updateUser(newUser);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateUserNullPassword() throws Exception {
        LOGGER.debug("test: updateUserNullPassword()");
        User newUser = new User(USER_LOGIN1, null);
        userService.updateUser(newUser);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateUserEmptyPassword() throws Exception {
        LOGGER.debug("test: updateUserNullPassword()");
        User newUser = new User(USER_LOGIN1, "");
        userService.updateUser(newUser);
    }

    @Test
    public void testDeleteUser() throws Exception {
        LOGGER.debug("test: deleteUser()");
        int sizeBefore = userService.getAllUsers().size();
        userService.deleteUser(USER_ID1);
        Assert.assertTrue(sizeBefore - 1 == userService.getAllUsers().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteUserNullId() throws Exception {
        LOGGER.debug("test: deleteUserNullId()");
        userService.deleteUser(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteUserNegativeId() throws Exception {
        LOGGER.debug("test: deleteUserNullId()");
        userService.deleteUser(-10);
    }

    @Test
    public void testGetCountUsers() throws Exception {
        LOGGER.debug("test: getCountSsers()");
        Integer count = userService.getCountUsers(USER_LOGIN1);
        Assert.assertTrue(count == 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetCountUsersNullLogin() throws Exception {
        LOGGER.debug("test: deleteUserNullLogin()");
        userService.getCountUsers(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetCountUsersEmptyLogin() throws Exception {
        LOGGER.debug("test: deleteUserNullLogin()");
        userService.getCountUsers("");
    }

    @Test
    public void testGetUserDto() throws Exception {
        LOGGER.debug("test: getUserDto()");
        UserDto userDto = userService.getUserDto();
        Assert.assertNotNull(userDto);
        Assert.assertTrue(userDto.getTotal() > 0);
        Assert.assertTrue(userDto.getUsers().size() > 0);
    }
}