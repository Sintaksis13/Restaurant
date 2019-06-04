package com.epam.dao.impdao;

import com.epam.dao.AbstractDao;
import com.epam.entity.Table;
import com.epam.entity.User;
import com.epam.entity.type.Role;
import com.epam.entity.type.TableStatus;
import com.epam.entity.type.TableType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static com.epam.constants.NumericConstants.*;

public class UserDao extends AbstractDao<User> {
    private static final Logger LOG = LogManager.getLogger(UserDao.class);

    private static final String SELECT_ALL_USERS = "select * from public.\"USER\" left join public.\"TABLE\" on " +
            "public.\"USER\".\"Table_ID\"\n = public.\"TABLE\".\"Table_ID\";";
    private static final String FIND_USER_BY_LOGIN = "select * from public.\"USER\" left join public.\"TABLE\" on " +
            "public.\"USER\".\"Table_ID\"\n = public.\"TABLE\".\"Table_ID\" where \"Login\" = ?";
    private static final String FIND_USER_BY_EMAIL = "select * from public.\"USER\" left join public.\"TABLE\" on " +
            "public.\"USER\".\"Table_ID\"\n = public.\"TABLE\".\"Table_ID\" where \"Email\" = ?";
    private static final String UPDATE_USER = "update public.\"USER\" set \"Email\" = ?, \"Phone_Number\" = ?, " +
            "\"Password\" = md5(?), \"Table_ID\" = ? where \"Login\" = ?";
    private static final String DELETE_USER = "delete from public.\"USER\" where \"User_ID\" = ?";
    private static final String CREATE_USER = "insert into public.\"USER\" (\"Login\", " +
            "\"Password\", \"Email\", \"Phone_Number\") values (?, md5(?), ?, ?)";
    private static final String CHECK_PASSWORD = "select \"Password\" =  md5(?) as pswmatch " +
                                                 "from public.\"USER\" where \"Login\" = ?";

    private PreparedStatement preparedStatement = null;

    private Table table;

    public UserDao(Connection connection) {
        super(connection);
    }

    @Override
    public List<User> getAll() throws SQLException {
        preparedStatement = getPreparedStatement(SELECT_ALL_USERS);
        List<User> userList = new LinkedList<>();
        User user;

        try(ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt(FIRST));
                user.setLogin(resultSet.getString(SECOND));
                user.setPassword(resultSet.getString(THIRD));
                user.setEmail(resultSet.getString(FOURTH));
                if (resultSet.getString(SIXTH).equalsIgnoreCase(Role.USER.toString())) {
                    user.setRole(Role.USER);
                } else {
                    user.setRole(Role.ADMIN);
                }
                user.setPhoneNumber(resultSet.getString(SEVENTH));

                table = fillupTable(resultSet.getInt(EIGHTH), resultSet.getInt(NINTH), resultSet.getString(TENTH),
                                 resultSet.getString(ELEVENTH), resultSet.getInt(TWELFTH));

                user.setTable(table);

                userList.add(user);
            }
        } finally {
            closePreparedStatement(preparedStatement);
        }

        return userList;
    }

    public User findByLogin(String login) throws SQLException {
        LOG.debug("Try to find user with login = {}", login);
        preparedStatement = getPreparedStatement(FIND_USER_BY_LOGIN);
        preparedStatement.setString(FIRST, login);
        User user = null;

        try(ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt(FIRST));
                user.setLogin(resultSet.getString(SECOND));
                user.setPassword(resultSet.getString(THIRD));
                user.setEmail(resultSet.getString(FOURTH));

                if (resultSet.getString(SIXTH).equalsIgnoreCase(Role.USER.toString())) {
                    user.setRole(Role.USER);
                } else {
                    user.setRole(Role.ADMIN);
                }
                user.setPhoneNumber(resultSet.getString(SEVENTH));

                table = fillupTable(resultSet.getInt(EIGHTH), resultSet.getInt(NINTH), resultSet.getString(TENTH),
                                 resultSet.getString(ELEVENTH), resultSet.getInt(TWELFTH));

                user.setTable(table);
            }
        } finally {
            closePreparedStatement(preparedStatement);
        }

        return user;
    }

    public User findUserByEmail(String email) throws SQLException {
        preparedStatement = getPreparedStatement(FIND_USER_BY_EMAIL);
        preparedStatement.setString(FIRST, email);
        User user = null;

        try(ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                user = new User();
                user.setEmail(resultSet.getString(FOURTH));
            }
        } finally {
            closePreparedStatement(preparedStatement);
        }

        return user;
    }

    public int update(User user) throws SQLException {
        preparedStatement = getPreparedStatement(UPDATE_USER);
        preparedStatement.setString(FIRST, user.getEmail());
        preparedStatement.setString(SECOND, user.getPhoneNumber());
        preparedStatement.setString(THIRD, user.getPassword());
        preparedStatement.setLong(FOURTH, user.getTable().getId());
        preparedStatement.setString(FIFTH, user.getLogin());
        int result;

        try {
            result = preparedStatement.executeUpdate();
        } finally {
            closePreparedStatement(preparedStatement);
        }

        return result;
    }

    public int delete(int id) throws SQLException {
        return super.delete(id, DELETE_USER);
    }

    @Override
    public int create(User user) throws SQLException {
        int result;

        preparedStatement = getPreparedStatement(CREATE_USER);
        preparedStatement.setString(FIRST, user.getLogin());
        preparedStatement.setString(SECOND, user.getPassword());
        preparedStatement.setString(THIRD, user.getEmail());
        preparedStatement.setString(FOURTH, user.getPhoneNumber());

        try {
            result = preparedStatement.executeUpdate();
        } finally {
            closePreparedStatement(preparedStatement);
        }

        return result;
    }

    public boolean checkUserPassword(String login, String password) throws SQLException {
        boolean result = false;

        preparedStatement = getPreparedStatement(CHECK_PASSWORD);
        preparedStatement.setString(FIRST, password);
        preparedStatement.setString(SECOND, login);

        try(ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                result = resultSet.getBoolean(FIRST);
            }
        } finally {
            closePreparedStatement(preparedStatement);
        }

        return result;
    }

    private Table fillupTable(Object...args) {
        Table table = new Table();
        table.setId(Integer.parseInt(args[0].toString()));
        table.setSeatsNumber(Integer.parseInt(args[1].toString()));
        if (args[2].toString().equalsIgnoreCase("common")) {
            table.setValue(TableType.COMMON);
        } else {
            table.setValue(TableType.VIP);
        }
        if (args[3].toString().equalsIgnoreCase("active")) {
            table.setStatus(TableStatus.ACTIVE);
        } else {
            table.setStatus(TableStatus.BOOKED);
        }
        table.setReservationTime(Integer.parseInt(args[4].toString()));

        return table;
    }
}
