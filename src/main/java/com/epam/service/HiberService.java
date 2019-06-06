package com.epam.service;

import com.epam.entity.BaseEntity;
import com.epam.exception.DaoException;

import java.util.List;

public interface HiberService<T extends BaseEntity> {
    void save(T object) throws DaoException;

    List<T> findAll();

    void deleteByName(String name);

    T findByName(String name);

    void update(T object);
}
