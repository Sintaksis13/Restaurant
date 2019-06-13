package com.epam.dao.implementation;

import com.epam.dao.interfaces.Dao;
import com.epam.entity.User;
import com.epam.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public class HibernateUserDao implements Dao<User> {
    private static final Logger LOG = LogManager.getLogger(HibernateUserDao.class);
    private static final Class<User> CLASS = User.class;

    @Override
    public User save(User object) throws DaoException {
        return null;
    }

    @Override
    public List<User> findAll() throws DaoException {
        return null;
    }

    public User deleteByLogin(String login) throws DaoException {
        return null;
    }

    public User findByLogin(String login) throws DaoException {
        return null;
    }

    @Override
    public User update(String name, User dish) throws DaoException {
        return null;
    }
}
