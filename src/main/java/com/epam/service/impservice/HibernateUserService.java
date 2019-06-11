package com.epam.service.impservice;

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
    @Override
    public Pair<DaoResult, User> save(User object) {
        return null;
    }

    @Override
    public Pair<DaoResult, List<User>> findAll() {
        return null;
    }

    @Override
    public Pair<DaoResult, User> deleteByName(String name) {
        return null;
    }

    @Override
    public Pair<DaoResult, User> findByName(String name) {
        return null;
    }

    @Override
    public Pair<DaoResult, Dish> update(String name, User object) {
        return null;
    }
}
