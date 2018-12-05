package com.epam.dao.impdao;

import com.epam.dao.AbstractDao;
import com.epam.entity.Table;
import com.epam.entity.type.TableStatus;
import com.epam.entity.type.TableType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static com.epam.constants.NumericConstants.*;

public class TableDao extends AbstractDao<Table> {

    private static final String SELECT_ALL_TABLES = "select * from public.\"TABLE\"";
    private static final String UPDATE_TABLE = "update public.\"TABLE\" set \"Number_Of_Seats\" = ?, \"Value\" = ?, " +
            "\"Status\" = ?, \"Reservation_Time\" = ? where \"Table_ID\" = ?";
    private static final String DELETE_TABLE = "delete * from public.\"TABLE\" where \"Table_ID\" = ?";
    private static final String CREATE_TABLE = "insert into public.\"TABLE\" (\"Number_Of_Seats\", " +
            "\"Value\", \"Status\") VALUES (?, ?, ?)";

    private PreparedStatement preparedStatement = null;

    public TableDao(Connection connection) {
        super(connection);
    }

    @Override
    public List<Table> getAll() throws SQLException{
        List<Table> tableList = new LinkedList<>();
        preparedStatement = getPreparedStatement(SELECT_ALL_TABLES);
        Table table;

        try(ResultSet resultSet = preparedStatement.executeQuery()) {
            while(resultSet.next()) {
                table = new Table();
                table.setId(resultSet.getInt(FIRST));
                table.setSeatsNumber(resultSet.getInt(SECOND));
                if (resultSet.getString(THIRD).equalsIgnoreCase(TableType.VIP.toString())) {
                    table.setValue(TableType.VIP);
                } else {
                    table.setValue(TableType.COMMON);
                }
                if (resultSet.getString(FOURTH).equalsIgnoreCase(TableStatus.ACTIVE.toString())) {
                    table.setStatus(TableStatus.ACTIVE);
                } else if (resultSet.getString(FOURTH).equalsIgnoreCase(TableStatus.BOOKED.toString())){
                    table.setStatus(TableStatus.BOOKED);
                } else {
                    table.setStatus(TableStatus.INACTIVE);
                }
                table.setReservationTime(resultSet.getInt(FIFTH));
                tableList.add(table);
            }
        } finally {
            closePreparedStatement(preparedStatement);
        }

        return tableList;
    }

    public void update(Table table) throws SQLException {
        preparedStatement = getPreparedStatement(UPDATE_TABLE);
        preparedStatement.setInt(FIRST, table.getSeatsNumber());
        if (table.getValue().toString().equalsIgnoreCase(TableType.VIP.toString())) {
            preparedStatement.setString(SECOND, TableType.VIP.toString());
        } else {
            preparedStatement.setString(SECOND, TableType.COMMON.toString());
        }
        preparedStatement.setString(THIRD, table.getStatus().toString());
        preparedStatement.setInt(FOURTH, table.getReservationTime());
        preparedStatement.setInt(FIFTH, table.getId());

        try {
            preparedStatement.executeUpdate();
        } finally {
            closePreparedStatement(preparedStatement);
        }
    }

    public int delete(int id) throws SQLException {
        return super.delete(id, DELETE_TABLE);
    }

    @Override
    public int create(Table table) throws SQLException{
        preparedStatement = getPreparedStatement(CREATE_TABLE);
        preparedStatement.setInt(SECOND, table.getSeatsNumber());
        if (table.getValue().toString().equalsIgnoreCase(TableType.VIP.toString())) {
            preparedStatement.setString(THIRD, TableType.VIP.toString());
        } else {
            preparedStatement.setString(THIRD, TableType.COMMON.toString());
        }
        preparedStatement.setString(FOURTH, table.getStatus().toString());

        try {
            return preparedStatement.executeUpdate();
        } finally {
            closePreparedStatement(preparedStatement);
        }
    }
}
