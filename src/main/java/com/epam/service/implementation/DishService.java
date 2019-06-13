package com.epam.service.implementation;

import com.epam.dao.result.DaoResult;
import com.epam.dao.implementation.DishDao;
import com.epam.entity.Dish;
import com.epam.exception.DaoException;
import com.epam.service.HibernateService;
import javafx.util.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("dishService")
@Transactional
public class DishService implements HibernateService<Dish> {
    private static final String UNEXPECTED_ERROR = "Unexpected error";

    private static final Logger LOG = LogManager.getLogger(DishService.class);
    private final DishDao dishDao;

    public DishService(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    @Override
    public Pair<DaoResult, Dish> save(Dish dish) {
        DaoResult result;
        Dish savedDish = null;
        try {
            savedDish = dishDao.save(dish);
            result = DaoResult.SUCCESSFUL;
        } catch (DaoException e) {
            result = DaoResult.FAILED.setMessage(e.getMessage());
        } catch (Throwable e) {
            LOG.error("Error occurred during saving dish = {}", dish, e);
            result = DaoResult.FAILED.setMessage(UNEXPECTED_ERROR);
        }

        return new Pair<>(result, savedDish);
    }

    @Override
    public Pair<DaoResult, List<Dish>> findAll() {
        DaoResult result;
        List<Dish> dishes = null;
        try {
            dishes = dishDao.findAll();
            result = DaoResult.SUCCESSFUL;
        } catch (DaoException e) {
            result = DaoResult.FAILED.setMessage(e.getMessage());
        } catch (Throwable e) {
            LOG.error("Error occurred during fetching dishes", e);
            result = DaoResult.FAILED.setMessage(UNEXPECTED_ERROR);
        }

        return new Pair<>(result, dishes);
    }

    public Pair<DaoResult, Dish> deleteByName(String dishName) {
        DaoResult result;
        Dish dish = null;
        try {
            dish = dishDao.deleteByName(dishName);
            result = DaoResult.SUCCESSFUL;
        } catch (DaoException e) {
            result = DaoResult.FAILED.setMessage(e.getMessage());
        } catch (Throwable e) {
            LOG.error("Error occurred during deleting dish = {}", dishName, e);
            result = DaoResult.FAILED.setMessage(UNEXPECTED_ERROR);
        }

        return new Pair<>(result, dish);
    }

    public Pair<DaoResult, Dish> findByName(String dishName) {
        DaoResult result;
        Dish dish = null;
        try {
            dish = dishDao.findByName(dishName);
            result = DaoResult.SUCCESSFUL;
        } catch (DaoException e) {
            result = DaoResult.FAILED.setMessage(e.getMessage());
        } catch (Throwable e) {
            LOG.error("Error occurred during fetching dish = {}", dishName, e);
            result = DaoResult.FAILED.setMessage(UNEXPECTED_ERROR);
        }

        return new Pair<>(result, dish);
    }

    @Override
    public Pair<DaoResult, Dish> update(String dishName, Dish dish) {
        DaoResult result;
        Dish updatedDish = null;
        try {
            updatedDish = dishDao.update(dishName, dish);
            result = DaoResult.SUCCESSFUL;
        } catch (DaoException e) {
            result = DaoResult.FAILED.setMessage(e.getMessage());
        } catch (Throwable e) {
            LOG.error("Error occurred during updating dish = {}", dish, e);
            result = DaoResult.FAILED.setMessage(UNEXPECTED_ERROR);
        }

        return new Pair<>(result, updatedDish);
    }
}