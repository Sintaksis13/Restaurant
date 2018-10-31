package com.epam.service.impservice;

import com.epam.dao.impdao.DishDao;
import com.epam.entity.Dish;
import com.epam.service.AbstractService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static com.epam.constants.ExceptionConstants.SQL_EXCEPTION;
import static com.epam.constants.NumericConstants.UNCHANGED_ROWS;

public class DishService extends AbstractService<Dish>{
    private Connection connection = AbstractService.getConnectionPool().getConnection();
    private DishDao dishDao = new DishDao(connection);
    private static final Logger LOG = LogManager.getLogger(DishService.class);

    public DishService() {
       super();
    }

    @Override
    public List<Dish> getAll() {
        List<Dish> listOfDishes = null;

        try {
            listOfDishes = dishDao.getAll();
        } catch (SQLException ex) {
            LOG.warn(SQL_EXCEPTION, ex.getMessage());
        }

        return listOfDishes;
    }

    public int update(Dish dish, String name) {
        int result = UNCHANGED_ROWS;

        try {
            result = dishDao.update(dish, name);
        } catch (SQLException ex) {
            LOG.warn(SQL_EXCEPTION, ex.getMessage());
        }

        return result;
    }

    @Override
    public int delete(int id) {
        int result = UNCHANGED_ROWS;

        try {
            result = dishDao.delete(id);
        } catch (SQLException ex) {
            LOG.warn(SQL_EXCEPTION, ex.getMessage());
        }

        return result;
    }

    @Override
    public int create(Dish dish) {
        int result = UNCHANGED_ROWS;

        try {
            result = dishDao.createUser(dish);
        } catch (SQLException ex) {
            LOG.warn(SQL_EXCEPTION, ex.getMessage());
        }

        return result;
    }

    public Dish getDish(String name) {
        Dish dish = null;

        try {
            dish = dishDao.findDishByName(name);
        } catch (SQLException ex) {
            LOG.warn(SQL_EXCEPTION, ex.getMessage());
        }

        return dish;
    }
}
