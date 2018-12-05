package com.epam.dao;

import com.epam.dao.idao.IDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.epam.constants.NumericConstants.FIRST;

public abstract class AbstractDao<E> implements IDao<E> {
    private static final Logger LOG = LogManager.getLogger(AbstractDao.class);

    private final Connection connection;

    protected AbstractDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int delete(int id, String sqlStatement) throws SQLException {
        PreparedStatement preparedStatement = getPreparedStatement(sqlStatement);
        preparedStatement.setInt(FIRST, id);
        int result;

        try {
            result = preparedStatement.executeUpdate();
        } finally {
            closePreparedStatement(preparedStatement);
        }

        return result;
    }

    protected PreparedStatement getPreparedStatement(String sqlStatement) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sqlStatement);
        } catch (SQLException ex) {
            LOG.warn("Preparing statement with sqlStatement={} was failed. Error message: {}", sqlStatement, ex.getMessage());
        }

        LOG.debug("Getting preparedStatement={} with sqlStatement={}", preparedStatement, sqlStatement);
        return preparedStatement;
    }

    protected void closePreparedStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                LOG.debug("Try to close preparedStatement={}", preparedStatement);
                preparedStatement.close();
            } catch (SQLException ex) {
                LOG.warn("Cannot close preparedStatement={}. Error message: {}", preparedStatement, ex.getMessage());
            }
        }
    }
}
