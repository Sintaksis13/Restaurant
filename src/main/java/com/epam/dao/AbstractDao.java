package com.epam.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static com.epam.constants.ExceptionConstants.SQL_EXCEPTION;
import static com.epam.constants.NumericConstants.FIRST;

public abstract class AbstractDao<E> {
    private static final Logger LOGGER = Logger.getLogger(AbstractDao.class);

    private final Connection connection;

    protected AbstractDao(Connection connection) {
        this.connection = connection;
    }

    public abstract List<E> getAll() throws SQLException;
    public abstract int create(E entity) throws SQLException;

    protected int delete(int id, String sql) throws SQLException {
        PreparedStatement preparedStatement = getPreparedStatement(sql);
        preparedStatement.setInt(FIRST, id);
        int result;

        try {
            result = preparedStatement.executeUpdate();
        } finally {
            closePreparedStatement(preparedStatement);
        }

        return result;
    }

    protected PreparedStatement getPreparedStatement(String sql) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException ex) {
            LOGGER.warn(SQL_EXCEPTION + ex.getMessage());
        }

        return preparedStatement;
    }

    protected void closePreparedStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException ex) {
                LOGGER.warn(SQL_EXCEPTION + ex.getMessage());
            }
        }
    }
}
