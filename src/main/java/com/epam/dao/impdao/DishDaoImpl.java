package com.epam.dao.impdao;

import com.epam.dao.HiberAbstractDao;
import com.epam.dao.idao.DishDao;
import com.epam.entity.Dish;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

//TODO implement all methods by using NamedQuery or smth else

@Repository("dishDao")
public class DishDaoImpl extends HiberAbstractDao<Dish> implements DishDao {
    @Autowired
    public DishDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void saveDish(Dish dish) {
        persist(dish);
    }

    @Override
    public List<Dish> findAllDishes() {
        return getSession().createQuery(getAllQuery(Dish.class)).list();
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
