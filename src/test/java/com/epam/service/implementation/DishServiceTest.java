package com.epam.service.implementation;

import com.epam.dao.implementation.DishDao;
import com.epam.dao.result.DaoResult;
import com.epam.entity.Dish;
import com.epam.exception.DaoException;
import javafx.util.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DishServiceTest {
    @Mock
    private DishDao dishDao;

    private DishService dishService;
    private final Dish testDish = new Dish("test", "test", 100.0);

    @Test
    public void testSave_success() throws DaoException {
        when(dishDao.save(any(Dish.class))).thenReturn(testDish);
        dishService = new DishService(dishDao);

        Pair<DaoResult, Dish> saveResult = dishService.save(testDish);

        assertEquals(DaoResult.SUCCESSFUL, saveResult.getKey());
        assertEquals(testDish, saveResult.getValue());
    }

    @Test
    public void testSave_fail_causedByThrowingDaoException() throws DaoException {
        String exceptionMessage = "Test exception";

        when(dishDao.save(any(Dish.class))).thenThrow(new DaoException(exceptionMessage));
        dishService = new DishService(dishDao);

        Pair<DaoResult, Dish> saveResult = dishService.save(testDish);

        assertEquals(DaoResult.FAILED, saveResult.getKey());
        assertEquals(exceptionMessage, saveResult.getKey().getMessage());
        assertNull(saveResult.getValue());
    }

    @Test
    public void testFindAll_success() throws DaoException {
        when(dishDao.findAll()).thenReturn(Collections.singletonList(testDish));
        dishService = new DishService(dishDao);

        Pair<DaoResult, List<Dish>> fetchResult = dishService.findAll();

        assertEquals(DaoResult.SUCCESSFUL, fetchResult.getKey());
        assertEquals(Collections.singletonList(testDish), fetchResult.getValue());
    }

    @Test
    public void testFindAll_fail_causedByThrowingDaoException() throws DaoException {
        String exceptionMessage = "Test exception";

        when(dishDao.findAll()).thenThrow(new DaoException(exceptionMessage));
        dishService = new DishService(dishDao);

        Pair<DaoResult, List<Dish>> fetchResult = dishService.findAll();

        assertEquals(DaoResult.FAILED, fetchResult.getKey());
        assertEquals(exceptionMessage, fetchResult.getKey().getMessage());
        assertNull(fetchResult.getValue());
    }

    @Test
    public void testDeleteByName_success() throws DaoException {
        when(dishDao.deleteByName(testDish.getName())).thenReturn(testDish);
        dishService = new DishService(dishDao);

        Pair<DaoResult, Dish> deleteResult = dishService.deleteByName(testDish.getName());

        assertEquals(DaoResult.SUCCESSFUL, deleteResult.getKey());
        assertEquals(testDish, deleteResult.getValue());
    }

    @Test
    public void testDeleteByName_fail_causedByThrowingDaoException() throws DaoException {
        String exceptionMessage = "Test exception";

        when(dishDao.deleteByName(anyString())).thenThrow(new DaoException(exceptionMessage));
        dishService = new DishService(dishDao);

        Pair<DaoResult, Dish> deleteResult = dishService.deleteByName(testDish.getName());

        assertEquals(DaoResult.FAILED, deleteResult.getKey());
        assertEquals(exceptionMessage, deleteResult.getKey().getMessage());
        assertNull(deleteResult.getValue());
    }

    @Test
    public void testFindByName_success() throws DaoException {
        when(dishDao.findByName(testDish.getName())).thenReturn(testDish);
        dishService = new DishService(dishDao);

        Pair<DaoResult, Dish> fetchResult = dishService.findByName(testDish.getName());

        assertEquals(DaoResult.SUCCESSFUL, fetchResult.getKey());
        assertEquals(testDish, fetchResult.getValue());
    }

    @Test
    public void testFindByName_fail_causedByThrowingDaoException() throws DaoException {
        String exceptionMessage = "Test exception";

        when(dishDao.findByName(anyString())).thenThrow(new DaoException(exceptionMessage));
        dishService = new DishService(dishDao);

        Pair<DaoResult, Dish> fetchResult = dishService.findByName(testDish.getName());

        assertEquals(DaoResult.FAILED, fetchResult.getKey());
        assertEquals(exceptionMessage, fetchResult.getKey().getMessage());
        assertNull(fetchResult.getValue());
    }

    @Test
    public void testUpdate_success() throws DaoException {
        String newTestDishName = "newTestDish";

        when(dishDao.update(anyString(), any(Dish.class))).thenReturn(testDish);
        dishService = new DishService(dishDao);

        Pair<DaoResult, Dish> updateResult = dishService.update(newTestDishName, testDish);

        assertEquals(DaoResult.SUCCESSFUL, updateResult.getKey());
        assertEquals(testDish, updateResult.getValue());
    }

    @Test
    public void testUpdate_fail_causedByThrowingDaoException() throws DaoException {
        String newTestDishName = "newTestDish";
        String exceptionMessage = "Test exception";

        when(dishDao.update(anyString(), any(Dish.class))).thenThrow(new DaoException(exceptionMessage));
        dishService = new DishService(dishDao);

        Pair<DaoResult, Dish> updateResult = dishService.update(newTestDishName, testDish);

        assertEquals(DaoResult.FAILED, updateResult.getKey());
        assertEquals(exceptionMessage, updateResult.getKey().getMessage());
        assertNull(updateResult.getValue());
    }
}