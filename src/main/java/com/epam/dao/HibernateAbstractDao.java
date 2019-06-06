package com.epam.dao;

import com.epam.entity.BaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;

public abstract class HibernateAbstractDao<T extends BaseEntity> {
    private final SessionFactory sessionFactory;

    public HibernateAbstractDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected CriteriaBuilder getCriteriaBuilder() {
        return getSession().getCriteriaBuilder();
    }

    protected CriteriaQuery<T> createQuery(Class<T> tClass) {
        return getSession().getCriteriaBuilder().createQuery(tClass);
    }

    protected CriteriaUpdate<T> createUpdate(Class<T> tClass) {
        return getSession().getCriteriaBuilder().createCriteriaUpdate(tClass);
    }

    protected void persist(T object) {
        getSession().persist(object);
    }

    protected void delete(T object) {
        getSession().delete(object);
    }
}
