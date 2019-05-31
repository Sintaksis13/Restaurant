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

    public void persist(T object) {
        try {
            getSession().persist(object);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void delete(T object) {
        getSession().delete(object);
    }
}
