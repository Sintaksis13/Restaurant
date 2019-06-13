package com.epam.dao.interfaces;

import com.epam.exception.DaoException;

import java.util.List;

public interface Dao<T> {
    T save(T object) throws DaoException;

    List<T> findAll() throws DaoException;

    T update(String name, T dish) throws DaoException;
}
