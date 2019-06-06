package com.epam.service.impservice;

import com.epam.dao.DaoResult;
import com.epam.dao.impdao.DishDaoImpl;
import com.epam.entity.Dish;
import com.epam.exception.DaoException;
import com.epam.service.HiberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("dishService")
@Transactional
public class DishServiceImpl implements HiberService<Dish> {
    private final DishDaoImpl dishDao;

    public DishServiceImpl(DishDaoImpl dishDao) {
        this.dishDao = dishDao;
    }

    @Override
    public void save(Dish dish) throws DaoException {
        DaoResult result = dishDao.saveDish(dish);
        if (result != DaoResult.SUCCESSFUL) {
            throw new DaoException("Save result is not successful!");
        }
    }

    @Override
    public List<Dish> findAll() {
        return dishDao.findAllDishes();
    }

    @Override
    public void deleteByName(String name) {
        dishDao.deleteDishByName(name);
    }

    @Override
    public Dish findByName(String name) {
        return dishDao.findDishByName(name);
    }

    @Override
    public void update(Dish dish) {
        dishDao.updateDish(dish);
    }
}
