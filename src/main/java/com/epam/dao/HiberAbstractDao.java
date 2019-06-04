package com.epam.dao;

import com.epam.entity.BaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaQuery;

public abstract class HiberAbstractDao<T extends BaseEntity> {
    private final SessionFactory sessionFactory;

    public HiberAbstractDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected CriteriaQuery<T> getAllQuery(Class<T> tClass) {
        return getSession().getCriteriaBuilder().createQuery(tClass);
    }

    protected void persist(T object) {
        getSession().persist(object);
    }

    protected void delete(T object) {
        getSession().delete(object);
    }
}