package com.epam.brest.course2015.service;

import com.epam.brest.course2015.dao.UserDao;
import com.epam.brest.course2015.domain.User;
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
    public User getUserById(Integer userId) {
        LOGGER.debug("getUserById({})", userId);
        Assert.notNull(userId);
        return userDao.getUserById(userId);
    }

    @Override
    public User getUserByLogin(String login) {
        LOGGER.debug("GetUSerByLogin({})", login);
        Assert.notNull(login);
        return userDao.getUserByLogin(login);
    }

    @Override
    public Integer addUser(User user) {
        LOGGER.debug("addUser(user) {}", user.getLogin());
        Assert.notNull(user, "User should not be null.");
        Assert.isNull(user.getUserId());
        Assert.notNull(user.getLogin());
        Assert.hasText(user.getLogin());
        return userDao.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        LOGGER.debug("updateUser(user) {}", user.getLogin());
        Assert.notNull(user, "User should not be null.");
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(Integer userId) {
        LOGGER.debug("deleteUser({})", userId);
        Assert.notNull(userId);
        userDao.deleteUser(userId);
    }
}
