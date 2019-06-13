package com.epam.service.implementation;

import com.epam.dao.implementation.HibernateUserDao;
import com.epam.dao.result.DaoResult;
import com.epam.entity.User;
import com.epam.entity.type.Role;
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
public class HibernateUserServiceTest {
    @Mock
    private HibernateUserDao userDao;

    private HibernateUserService userService;
    private User testUser = new User("test", "test", "test@email.com", "7357", Role.USER);

    @Test
    public void testSave_success() throws DaoException {
        when(userDao.save(any(User.class))).thenReturn(testUser);
        userService = new HibernateUserService(userDao);

        Pair<DaoResult, User> saveResult = userService.save(testUser);

        assertEquals(DaoResult.SUCCESSFUL, saveResult.getKey());
        assertEquals(testUser, saveResult.getValue());
    }

    @Test
    public void testSave_fail_causedByThrowingDaoException() throws DaoException {
        String exceptionMessage = "Test exception";

        when(userDao.save(any(User.class))).thenThrow(new DaoException(exceptionMessage));
        userService = new HibernateUserService(userDao);

        Pair<DaoResult, User> saveResult = userService.save(testUser);

        assertEquals(DaoResult.FAILED, saveResult.getKey());
        assertEquals(exceptionMessage, saveResult.getKey().getMessage());
        assertNull(saveResult.getValue());
    }

    @Test
    public void testFindAll_success() throws DaoException {
        when(userDao.findAll()).thenReturn(Collections.singletonList(testUser));
        userService = new HibernateUserService(userDao);

        Pair<DaoResult, List<User>> fetchResult = userService.findAll();

        assertEquals(DaoResult.SUCCESSFUL, fetchResult.getKey());
        assertEquals(Collections.singletonList(testUser), fetchResult.getValue());
    }

    @Test
    public void testFindAll_fail_causedByThrowingDaoException() throws DaoException {
        String exceptionMessage = "Test exception";

        when(userDao.findAll()).thenThrow(new DaoException(exceptionMessage));
        userService = new HibernateUserService(userDao);

        Pair<DaoResult, List<User>> fetchResult = userService.findAll();

        assertEquals(DaoResult.FAILED, fetchResult.getKey());
        assertEquals(exceptionMessage, fetchResult.getKey().getMessage());
        assertNull(fetchResult.getValue());
    }

    @Test
    public void testDeleteByLogin_success() throws DaoException {
        when(userDao.deleteByLogin(testUser.getLogin())).thenReturn(testUser);
        userService = new HibernateUserService(userDao);

        Pair<DaoResult, User> deleteResult = userService.deleteByLogin(testUser.getLogin());

        assertEquals(DaoResult.SUCCESSFUL, deleteResult.getKey());
        assertEquals(testUser, deleteResult.getValue());
    }

    @Test
    public void testDeleteByLogin_fail_causedByThrowingDaoException() throws DaoException {
        String exceptionMessage = "Test exception";

        when(userDao.findByLogin(anyString())).thenThrow(new DaoException(exceptionMessage));
        userService = new HibernateUserService(userDao);

        Pair<DaoResult, User> deleteResult = userService.deleteByLogin(testUser.getLogin());

        assertEquals(DaoResult.FAILED, deleteResult.getKey());
        assertEquals(exceptionMessage, deleteResult.getKey().getMessage());
        assertNull(deleteResult.getValue());
    }

    @Test
    public void testFindByLogin_success() throws DaoException {
        when(userDao.findByLogin(testUser.getLogin())).thenReturn(testUser);
        userService = new HibernateUserService(userDao);

        Pair<DaoResult, User> fetchResult = userService.findByLogin(testUser.getLogin());

        assertEquals(DaoResult.SUCCESSFUL, fetchResult.getKey());
        assertEquals(testUser, fetchResult.getValue());
    }

    @Test
    public void testFindByLogin_fail_causedByThrowingDaoException() throws DaoException {
        String exceptionMessage = "Test exception";

        when(userDao.findByLogin(anyString())).thenThrow(new DaoException(exceptionMessage));
        userService = new HibernateUserService(userDao);

        Pair<DaoResult, User> fetchResult = userService.findByLogin(testUser.getLogin());

        assertEquals(DaoResult.FAILED, fetchResult.getKey());
        assertEquals(exceptionMessage, fetchResult.getKey().getMessage());
        assertNull(fetchResult.getValue());
    }

    @Test
    public void testUpdate_success() throws DaoException {
        String newTestDishName = "newTestDish";

        when(userDao.update(anyString(), any(User.class))).thenReturn(testUser);
        userService = new HibernateUserService(userDao);

        Pair<DaoResult, User> updateResult = userService.update(newTestDishName, testUser);

        assertEquals(DaoResult.SUCCESSFUL, updateResult.getKey());
        assertEquals(testUser, updateResult.getValue());
    }

    @Test
    public void testUpdate_fail_causedByThrowingDaoException() throws DaoException {
        String newTestDishName = "newTestDish";
        String exceptionMessage = "Test exception";

        when(userDao.update(anyString(), any(User.class))).thenThrow(new DaoException(exceptionMessage));
        userService = new HibernateUserService(userDao);

        Pair<DaoResult, User> updateResult = userService.update(newTestDishName, testUser);

        assertEquals(DaoResult.FAILED, updateResult.getKey());
        assertEquals(exceptionMessage, updateResult.getKey().getMessage());
        assertNull(updateResult.getValue());
    }
}