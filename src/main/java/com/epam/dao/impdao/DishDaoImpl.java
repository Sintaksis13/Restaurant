package com.epam.dao.impdao;

import com.epam.dao.HibernateAbstractDao;
import com.epam.dao.idao.Dao;
import com.epam.entity.Dish;
import com.epam.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("dishDao")
public class DishDaoImpl extends HibernateAbstractDao<Dish> implements Dao<Dish> {
    private static final Logger LOG = LogManager.getLogger(DishDaoImpl.class);
    private static final Class<Dish> CLASS = Dish.class;

    @Autowired
    public DishDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Dish save(Dish dish) throws DaoException {
        String dishName = dish.getName();
        Dish dishByName = findByName(dishName);
        if (dishByName != null) {
            LOG.warn("Dish with name={} already exists", dishName);
            throw new DaoException(String.format("Dish '%s' already exists", dishName));
        }

        try {
            persist(dish);
            LOG.info("Dish = {} has been saved", dish);
        } catch (Exception e) {
            LOG.error("Error occurred during saving dish = {}", dish, e);
            throw new DaoException("Error during saving dish");
        }

        return dish;
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
    public Dish deleteByName(String dishName) throws DaoException {
        Dish dishByName = findByName(dishName);
        if (dishByName == null) {
            LOG.warn("Dish with name={} doesn't exists", dishName);
            throw new DaoException(String.format("Dish '%s' doesn't exists", dishName));
        }

        try {
            delete(dishByName);
            LOG.info("Dish = {} has been successfully deleted", dishByName);
        } catch (Exception e) {
            LOG.error("Error occurred during deleting dish = {}", dishByName, e);
            throw new DaoException("Error during deleting dish");
        }

        return dishByName;
    }

    @Override
    public Dish findByName(String dishName) throws DaoException {
        Dish dish;
        try {
            CriteriaQuery<Dish> query = createQuery(CLASS);
            Root<Dish> root = query.from(CLASS);
            query.select(root).where(getCriteriaBuilder().equal(root.get("name"), dishName));
            dish = getSession().createQuery(query).stream().findFirst().orElse(null);
        } catch (NoResultException e) {
            LOG.warn("Dish with name '{}' was not found", dishName);
            return null;
        } catch (Exception e) {
            LOG.error("Error occurred during fetching dish from database", e);
            throw new DaoException("Error during fetching dish");
        }

        return dish;
    }

    @Override
    public Dish update(Dish dish) throws DaoException {
        Dish oldDish = findByName(dish.getName());
        if (oldDish == null) {
            throw new DaoException(String.format("Dish with name '%s' was not found", dish.getName()));
        } else if (oldDish.equals(dish)) {
            throw new DaoException("Dishes are equals");
        }

        CriteriaUpdate<Dish> update = createUpdate(CLASS);
        Root<Dish> root = update.from(CLASS);
        update.set("description", dish.getDescription());
        update.set("price", dish.getPrice());
        update.where(getCriteriaBuilder().equal(root.get("name"), dish.getName()));
        int affectedRecordsNumber = getSession().createQuery(update).executeUpdate();

        if (affectedRecordsNumber < 1) {
            LOG.warn("No one record was not affected, dish={}", dish);
            throw new DaoException("No one record was not affected");
        }

        return dish;
    }
}
