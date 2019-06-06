package com.epam.dao.idao;

import com.epam.exception.DaoException;

import java.util.List;

public interface Dao<T> {
    void save(T object) throws DaoException;

    List<T> findAll() throws DaoException;

    void deleteByName(String name);

    T findByName(String name) throws DaoException;

    void update(T dish);
}
