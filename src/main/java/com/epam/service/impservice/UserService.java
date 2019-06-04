package com.epam.service.impservice;

import com.epam.dao.impdao.UserDao;
import com.epam.entity.User;
import com.epam.service.AbstractService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static com.epam.constants.ExceptionConstants.SQL_EXCEPTION;
import static com.epam.constants.NumericConstants.UNCHANGED_ROWS;

public class UserService extends AbstractService<User> {
    private static final Logger LOG = LogManager.getLogger(UserService.class);

    private Connection connection = AbstractService.getConnectionPool().getConnection();

    public UserService() {
        super();
    }

    private UserDao userDao = new UserDao(connection);

    @Override
    public List<User> getAll() {
        List<User> userList = null;

        try {
            userList = userDao.getAll();
        } catch (SQLException ex) {
            LOG.warn(SQL_EXCEPTION + ex.getMessage());
        }

        return userList;
    }

    public User getAccount(String login) {
        User user = null;

        try {
            user = userDao.findByLogin(login);
        } catch (SQLException ex) {
            LOG.warn(SQL_EXCEPTION + ex.getMessage());
        }

        return user;
    }

    public String checkRoleByLogin(String login) {
        User user = null;

        try {
            user = userDao.findByLogin(login);
        } catch (SQLException ex) {
            LOG.warn(SQL_EXCEPTION + ex.getMessage());
        }

        return user != null ? user.getRole().toString() : null;
    }

    public boolean checkForLogIn(String login, String password) {
        boolean result = false;

        User user = null;

        try {
            user = userDao.findByLogin(login);
        } catch (SQLException ex) {
            LOG.warn(SQL_EXCEPTION + ex.getMessage());
        }

        try {
            result = (user != null && userDao.checkUserPassword(login, password));
        } catch (SQLException ex) {
            LOG.warn(SQL_EXCEPTION + ex.getMessage());
        }

        return result;
    }

    public boolean checkForRegistration(String login, String email) {
        User user = null;
        boolean result = false;

        try {
            user = userDao.findByLogin(login);
        } catch (SQLException ex) {
            LOG.warn(SQL_EXCEPTION + ex.getMessage());
        }

        if (user == null) {
            try {
                user = userDao.findUserByEmail(email);
            } catch (SQLException ex) {
                LOG.warn(SQL_EXCEPTION + ex.getMessage());
            }

            if (user == null) {
                result = true;
            }
        }

        return result;
    }

    public int updateUserInfo(User user) {
        int result = UNCHANGED_ROWS;

        try {
            if (userDao.findUserByEmail(user.getEmail()) == null
                    || userDao.findByLogin(user.getLogin()).getEmail().equals(user.getEmail())) {
                result = userDao.update(user);
            } 
        } catch (SQLException ex) {
            LOG.warn(SQL_EXCEPTION + ex.getMessage());
        }

        return result;
    }

    public void changeUserTable(User user) {
        try {
            userDao.update(user);
        } catch (SQLException ex) {
            LOG.warn(SQL_EXCEPTION + ex.getMessage());
        }
    }

    @Override
    public int delete(int id) {
        int result = UNCHANGED_ROWS;

        try {
            result = userDao.delete(id);
        } catch (SQLException ex) {
            LOG.warn(SQL_EXCEPTION + ex.getMessage());
        }

        return result;
    }

    @Override
    public int create(User user) {
        int result = UNCHANGED_ROWS;

        try {
            result = userDao.create(user);
        } catch (SQLException ex) {
            LOG.warn(SQL_EXCEPTION + ex.getMessage());
        }

        return result;
    }
}
