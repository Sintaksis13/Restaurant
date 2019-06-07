package com.epam.service;

import com.epam.dao.DaoResult;
import com.epam.entity.BaseEntity;
import com.epam.entity.Dish;
import javafx.util.Pair;

import java.util.List;

public interface HibernateService<T extends BaseEntity> {
    Pair<DaoResult, T> save(T object);

    Pair<DaoResult, List<T>> findAll();

    Pair<DaoResult, T> deleteByName(String name);

    Pair<DaoResult, T> findByName(String name);

    Pair<DaoResult, Dish> update(String name, T object);
}
