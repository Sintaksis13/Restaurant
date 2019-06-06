package com.epam.dao.impdao;

import com.epam.dao.DaoResult;
import com.epam.dao.HiberAbstractDao;
import com.epam.dao.idao.DishDao;
import com.epam.entity.Dish;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

//TODO implement all methods by using NamedQuery or smth else

@Repository("dishDao")
public class DishDaoImpl extends HiberAbstractDao<Dish> implements DishDao {
    private static final Logger LOG = LogManager.getLogger(DishDaoImpl.class);
    private static final Class<Dish> CLASS = Dish.class;

    @Autowired
    public DishDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public DaoResult saveDish(Dish dish) {
        DaoResult result;
        try {
            persist(dish);
            LOG.info("Dish = {} has been saved", dish);
            result = DaoResult.SUCCESSFUL;
        } catch (Exception e) {
            LOG.error("Error occurred during saving dish = {}", dish, e);
            result = DaoResult.FAILED;
        }

        return result;
    }

    @Override
    public List<Dish> findAllDishes() {
        List<Dish> dishes;
        try {
            CriteriaQuery<Dish> query = getSession().getCriteriaBuilder().createQuery(CLASS);
            dishes = getSession().createQuery(query.select(query.from(CLASS))).list();
        } catch (Exception e) {
            LOG.error("Error occurred during fetching dishes from database", e);
            dishes = new ArrayList<>();
        }

        return dishes;
    }

    @Override
    public void deleteDishByName(String name) {

    }

    @Override
    public Dish findDishByName(String name) {
        return null;
    }

    @Override
    public void updateDish(Dish dish) {

    }
}
