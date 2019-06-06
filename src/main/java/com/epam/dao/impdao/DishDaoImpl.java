package com.epam.dao.impdao;

import com.epam.dao.HibernateAbstractDao;
import com.epam.dao.idao.Dao;
import com.epam.entity.Dish;
import com.epam.exception.DaoException;
import com.epam.exception.EntityAlreadyExistException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

//TODO implement all methods by using NamedQuery or smth else

@Repository("dishDao")
public class DishDaoImpl extends HibernateAbstractDao<Dish> implements Dao<Dish> {
    private static final Logger LOG = LogManager.getLogger(DishDaoImpl.class);
    private static final Class<Dish> CLASS = Dish.class;

    @Autowired
    public DishDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void save(Dish dish) throws DaoException {
        String dishName = dish.getName();
        Dish dishByName = findByName(dishName);
        if (dishByName != null) {
            LOG.warn("Dish with name={} already exists", dishName);
            throw new EntityAlreadyExistException(String.format("Dish '%s' already exists", dishName));
        }

        Transaction transaction = null;
        try {
            transaction = getSession().beginTransaction();
            persist(dish);
            transaction.commit();
            LOG.info("Dish = {} has been saved", dish);
        } catch (Exception e) {
            LOG.error("Error occurred during saving dish = {}", dish, e);
            if (transaction != null) {
                transaction.rollback();
            }

            throw new DaoException("Error during saving dish");
        }
    }

    @Override
    public List<Dish> findAll() throws DaoException {
        List<Dish> dishes;
        try {
            CriteriaQuery<Dish> query = createQuery(CLASS);
            Root<Dish> root = query.from(CLASS);
            query.select(root);
            dishes = getSession().createQuery(query).list();
        } catch (Exception e) {
            LOG.error("Error occurred during fetching dishes from database", e);
            throw new DaoException("Error during fetching dishes");
        }

        return dishes;
    }

    @Override
    public void deleteByName(String name) {

    }

    @Override
    public Dish findByName(String name) throws DaoException {
        Dish dish;
        try {
            CriteriaQuery<Dish> query = createQuery(CLASS);
            Root<Dish> root = query.from(CLASS);
            query.select(root).where(getCriteriaBuilder().equal(root.get("name"), name));
            dish = getSession().createQuery(query).getSingleResult();
        } catch (Exception e) {
            LOG.error("Error occurred during fetching dish from database", e);
            throw new DaoException("Error during fetching dish");
        }

        return dish;
    }

    @Override
    public void update(Dish dish) {

    }
}
