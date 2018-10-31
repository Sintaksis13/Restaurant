package com.epam.service.impservice;

import com.epam.dao.impdao.TableDao;
import com.epam.entity.Table;
import com.epam.entity.type.TableStatus;
import com.epam.service.AbstractService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static com.epam.constants.ExceptionConstants.SQL_EXCEPTION;
import static com.epam.constants.NumericConstants.UNCHANGED_ROWS;

public class TableService extends AbstractService<Table> {
    private static final Logger LOGGER = LogManager.getLogger(TableService.class);

    private Connection connection = AbstractService.getConnectionPool().getConnection();

    public TableService() {
        super();
    }

    private TableDao tableDao = new TableDao(connection);

    @Override
    public List<Table> getAll() {
        List<Table> listOfTables = null;

        try {
            listOfTables = tableDao.getAll();
        } catch (SQLException ex) {
            LOGGER.warn(SQL_EXCEPTION + ex.getMessage());
        }

        return listOfTables;
    }

    public int reserve(Table wantedTable) {
        List<Table> tableList = new LinkedList<>();
        int tableId = UNCHANGED_ROWS;

        try {
            tableList = tableDao.getAll();
        } catch (SQLException ex) {
            LOGGER.warn(SQL_EXCEPTION + ex.getMessage());
        }

        for (Table table: tableList) {
            if (table.getStatus() == TableStatus.ACTIVE && table.getSeatsNumber() == wantedTable.getSeatsNumber()
                    && table.getReservationTime() != wantedTable.getReservationTime()) {
                wantedTable.setId(table.getId());
                tableId = table.getId();

                try {
                    tableDao.update(wantedTable);
                } catch (SQLException ex) {
                    LOGGER.warn(SQL_EXCEPTION + ex.getMessage());
                }

                break;
            }
        }

        return tableId;
    }

    @Override
    public int delete(int id) {
        int result = UNCHANGED_ROWS;

        try {
            result = tableDao.delete(id);
        } catch (SQLException ex) {
            LOGGER.warn(SQL_EXCEPTION + ex.getMessage());
        }

        return result;
    }

    @Override
    public int create(Table table) {
        int result = UNCHANGED_ROWS;

        try {
            result = tableDao.createUser(table);
        } catch (SQLException ex) {
            LOGGER.warn(SQL_EXCEPTION + ex.getMessage());
        }

        return result;
    }
}
