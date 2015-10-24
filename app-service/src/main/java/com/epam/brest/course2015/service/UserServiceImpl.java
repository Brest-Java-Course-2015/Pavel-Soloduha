package com.epam.brest.course2015.service;

import com.epam.brest.course2015.dao.UserDao;
import com.epam.brest.course2015.domain.User;
import com.epam.brest.course2015.dto.UserDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by pavel on 15.10.15.
 */
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger();

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() {
        LOGGER.debug("getAllUsers()");
        return userDao.getAllUsers();
    }

    @Override
    public Integer addUser(User user) {
        Assert.notNull(user, "User should not be null.");
        LOGGER.debug("addUser(): user login = {} ", user.getLogin());
        Assert.isNull(user.getUserId(), "User Id should be null.");
        Assert.hasText(user.getLogin(), "User login should not be null.");
        Assert.hasText(user.getPassword(), "User password should not be null.");

        if (userDao.getCountUsers(user.getLogin()) > 0) {
            throw new IllegalArgumentException("User login should be unique.");
        }
        return userDao.addUser(user);
    }

    @Override
    public User getUserById(Integer userId) {
        Assert.notNull(userId, "User Id should not be null.");
        LOGGER.debug("getUserByLogin(): user id = {} ", userId);
        Assert.isTrue(userId > 0);
        return userDao.getUserById(userId);
    }

    @Override
    public User getUserByLogin(String login) {
        Assert.hasText(login, "User login should not be null.");
        LOGGER.debug("getUserByLogin(): user login = {} ", login);
        return userDao.getUserByLogin(login);
    }

    @Override
    public Integer getCountUsers(String login) {
        Assert.hasText(login, "User login should not be null.");
        LOGGER.debug("getCountUsers(): user login = {} ", login);
        return userDao.getCountUsers(login);
    }

    @Override
    public void updateUser(User user) {
        Assert.notNull(user, "User should not be null.");
        LOGGER.debug("updateUser(): user login = {} ", user.getLogin());
        Assert.notNull(user.getUserId(), "User Id should not be null.");
        Assert.hasText(user.getPassword(), "User password should not be null.");
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(Integer userId) {
        Assert.notNull(userId, "User Id should not be null.");
        LOGGER.debug("deleteUser(): user id = {} ", userId);
        Assert.isTrue(userId > 0);
        userDao.deleteUser(userId);
    }

    @Override
    public void logUser(User user) {
        LOGGER.debug("logUser(): user id = {} ", user.getUserId());
    }

    @Override
    public UserDto getUserDto() {
        return null;
    }
}