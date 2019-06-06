package com.epam.service;

import com.epam.dao.DaoResult;
import com.epam.entity.BaseEntity;

import java.util.List;

public interface HibernateService<T extends BaseEntity> {
    DaoResult save(T object);

    List<T> findAll();

    void deleteByName(String name);

    T findByName(String name);

    void update(T object);
}
