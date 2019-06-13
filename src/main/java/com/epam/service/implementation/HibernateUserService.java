package com.epam.service.implementation;

import com.epam.dao.interfaces.Dao;
import com.epam.dao.result.DaoResult;
import com.epam.entity.Dish;
import com.epam.entity.User;
import com.epam.service.HibernateService;
import javafx.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
public class HibernateUserService implements HibernateService<User> {
    private final Dao<User> userDao;

    public HibernateUserService(Dao<User> userDao) {
        this.userDao = userDao;
    }

    @Override
    public Pair<DaoResult, User> save(User object) {
        return null;
    }

    @Override
    public Pair<DaoResult, List<User>> findAll() {
        return null;
    }

    public Pair<DaoResult, User> deleteByLogin(String login) {
        return null;
    }

    public Pair<DaoResult, User> findByLogin(String login) {
        return null;
    }

    @Override
    public Pair<DaoResult, User> update(String name, User object) {
        return null;
    }
}
