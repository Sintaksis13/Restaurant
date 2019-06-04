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
    private static final Logger LOG = LogManager.getLogger(TableService.class);

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
            LOG.warn(SQL_EXCEPTION + ex.getMessage());
        }

        return listOfTables;
    }

    public long reserve(Table wantedTable) {
        List<Table> tableList = new LinkedList<>();
        long tableId = UNCHANGED_ROWS;

        try {
            tableList = tableDao.getAll();
        } catch (SQLException ex) {
            LOG.warn(SQL_EXCEPTION + ex.getMessage());
        }

        for (Table tab: tableList) {
            if (tab.getStatus() == TableStatus.ACTIVE && tab.getSeatsNumber() == wantedTable.getSeatsNumber()
                    && tab.getReservationTime() != wantedTable.getReservationTime()) {
                wantedTable.setId(tab.getId());
                tableId = tab.getId();
                
                try {
                    tableDao.update(wantedTable);
                } catch (SQLException ex) {
                    LOG.warn(SQL_EXCEPTION + ex.getMessage());
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
            LOG.warn(SQL_EXCEPTION + ex.getMessage());
        }

        return result;
    }

    @Override
    public int create(Table table) {
        int result = UNCHANGED_ROWS;

        try {
            result = tableDao.create(table);
        } catch (SQLException ex) {
            LOG.warn(SQL_EXCEPTION + ex.getMessage());
        }

        return result;
    }
}
