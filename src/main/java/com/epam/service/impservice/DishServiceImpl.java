package com.epam.service.impservice;

import com.epam.dao.DaoResult;
import com.epam.dao.impdao.DishDaoImpl;
import com.epam.entity.Dish;
import com.epam.exception.DaoException;
import com.epam.service.HiberDishService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("dishService")
@Transactional
public class DishServiceImpl implements HiberDishService {
    private final DishDaoImpl dishDao;

    public DishServiceImpl(DishDaoImpl dishDao) {
        this.dishDao = dishDao;
    }

    @Override
    public void saveDish(Dish dish) throws DaoException {
        DaoResult result = dishDao.saveDish(dish);
        if (result != DaoResult.SUCCESSFUL) {
            throw new DaoException("Save result is not successful!");
        }
    }

    @Override
    public List<Dish> findAllDishes() {
        return dishDao.findAllDishes();
    }

    @Override
    public void deleteDishByName(String name) {
        dishDao.deleteDishByName(name);
    }

    @Override
    public Dish findDishByName(String name) {
        return dishDao.findDishByName(name);
    }

    @Override
    public void updateDish(Dish dish) {
        dishDao.updateDish(dish);
    }
}
