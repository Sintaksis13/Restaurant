package com.epam.service;

import com.epam.connectionpool.ConnectionPool;

import java.util.List;
import java.util.ResourceBundle;

public abstract class AbstractService<E> {

    private static final ConnectionPool connectionPool;
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
    private static final String url = resourceBundle.getString("database.url");
    private static final String user = resourceBundle.getString("database.user");
    private static final String password = resourceBundle.getString("database.password");
    private static final String driverName = resourceBundle.getString("database.driverName");

    static {
        connectionPool = ConnectionPool.getInstance(driverName, url, user, password);
    }

    public abstract List<E> getAll();
    public abstract int delete(int id);
    public abstract int create(E entity);

    public static ConnectionPool getConnectionPool() {
        return connectionPool;
    }
}
