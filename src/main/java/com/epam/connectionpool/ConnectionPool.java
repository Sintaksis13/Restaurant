package com.epam.connectionpool;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.constants.ExceptionConstants.*;

public class ConnectionPool {
    private static ConnectionPool instance;
    private String driverName;
    private List<Connection> freeConnections = new ArrayList<>();
    private String url;
    private String user;
    private String password;

    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);

    private ConnectionPool(String driverName, String url, String user, String password) {
        this.driverName = driverName;
        this.url = url;
        this.user = user;
        this.password = password;
        init();
    }

    private void init() {
        try {
            Driver driver = (Driver) Class.forName(driverName).newInstance();
            DriverManager.registerDriver(driver);
        } catch (SQLException ex) {
            LOGGER.warn(SQL_EXCEPTION + ex.getMessage());
        } catch (ReflectiveOperationException ex) {
            LOGGER.warn(REFLECTIVE_OPERATION_EXCEPTION + ex.getMessage());
        }
    }

    static synchronized public ConnectionPool getInstance(String driverName, String url, String user, String password) {
        if (instance == null) {
            instance = new ConnectionPool(driverName, url, user, password);
        }

        return instance;
    }

    synchronized public Connection getConnection() {
        Connection con;
        if (!freeConnections.isEmpty()) {
            con = freeConnections.get(freeConnections.size()-1);
            freeConnections.remove(con);
            try {
                if (con.isClosed()) {
                    con = getConnection();
                }
            } catch (SQLException ex) {
                LOGGER.warn(SQL_EXCEPTION + ex.getMessage());
                con = getConnection();
            }
        } else {
            con = newConnection();
        }
        return con;
    }

    private Connection newConnection() {
        Connection con;
        try {
            if (user == null) {
                con = DriverManager.getConnection(url);
            } else {
                con = DriverManager.getConnection(url, user, password);
            }
        } catch (SQLException ex) {
            LOGGER.warn(SQL_EXCEPTION + ex.getMessage());
            con = null;
        }
        return con;
    }
}
