package com.epam.service.impservice;

import com.epam.dao.DaoResult;
import com.epam.dao.impdao.DishDaoImpl;
import com.epam.entity.Dish;
import com.epam.exception.DaoException;
import com.epam.service.HibernateService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("dishService")
@Transactional
public class DishServiceImpl implements HibernateService<Dish> {
    private static final Logger LOG = LogManager.getLogger(DishServiceImpl.class);
    private final DishDaoImpl dishDao;

    public DishServiceImpl(DishDaoImpl dishDao) {
        this.dishDao = dishDao;
    }

    @Override
    public DaoResult save(Dish dish) {
        DaoResult result;
        try {
            dishDao.save(dish);
            result = DaoResult.SUCCESSFUL;
        } catch (DaoException e) {
            result = DaoResult.FAILED.setMessage(e.getMessage());
        } catch (Exception e) {
            LOG.error("Error occurred during saving dish = {}", dish, e);
            result = DaoResult.FAILED.setMessage("Unexpected error");
        }

        return result;
    }

    @Override
    public List<Dish> findAll() {
        try {
            return dishDao.findAll();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void deleteByName(String name) {
        dishDao.deleteByName(name);
    }

    @Override
    public Dish findByName(String name) {
        try {
            return dishDao.findByName(name);
        } catch (DaoException e) {
            return null;
        }
    }

    @Override
    public void update(Dish dish) {
        dishDao.update(dish);
    }
}
