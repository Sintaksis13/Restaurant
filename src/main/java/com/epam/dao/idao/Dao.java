package com.epam.dao.idao;

import com.epam.entity.Dish;
import com.epam.exception.DaoException;

import java.util.List;

public interface Dao<T> {
    Dish save(T object) throws DaoException;

    List<T> findAll() throws DaoException;

    T deleteByName(String name) throws DaoException;

    T findByName(String name) throws DaoException;

    Dish update(T dish) throws DaoException;
}
