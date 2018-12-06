package com.epam.dao.impdao;

import com.epam.dao.AbstractDao;
import com.epam.entity.Dish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static com.epam.constants.NumericConstants.*;

public class DishDao extends AbstractDao<Dish> {
    private static final String SELECT_ALL_DISHES = "select * from public.\"DISH\"";
    private static final String UPDATE_DISH = "update public.\"DISH\" set \"Name\" = ?, " +
            "\"Description\" = ?, \"Price\" = ? where \"Name\" = ?";
    private static final String DELETE_DISH = "delete FROM public.\"DISH\" where \"Dish_ID\" = ?";
    private static final String CREATE_DISH = "insert into public.\"DISH\" (\"Name\", " +
            "\"Description\", \"Price\") VALUES (?, ?, ?)";
    private static final String FIND_DISH_BY_NAME = "select * from public.\"DISH\" where \"Name\" = ?";

    private PreparedStatement preparedStatement = null;

    public DishDao(Connection connection) {
        super(connection);
    }

    @Override
    public List<Dish> getAll() throws SQLException {
        List<Dish> listOfDishes = new LinkedList<>();
        preparedStatement = getPreparedStatement(SELECT_ALL_DISHES);

        try(ResultSet resultSet = preparedStatement.executeQuery()) {
            while(resultSet.next()) {
                Dish dish= new Dish();
                dish.setId(resultSet.getInt(FIRST));
                dish.setName(resultSet.getString(SECOND));
                dish.setDescription(resultSet.getString(THIRD));
                dish.setPrice(resultSet.getDouble(FOURTH));
                listOfDishes.add(dish);
            }
        } finally {
            closePreparedStatement(preparedStatement);
        }

        return listOfDishes;
    }

    public int update(Dish dish, String name) throws SQLException {
        int result;
        preparedStatement = getPreparedStatement(UPDATE_DISH);
        preparedStatement.setString(FIRST, dish.getName());
        preparedStatement.setString(SECOND, dish.getDescription());
        preparedStatement.setDouble(THIRD, dish.getPrice());
        preparedStatement.setString(FOURTH, name);
        try {
            result = preparedStatement.executeUpdate();
        } finally {
            closePreparedStatement(preparedStatement);
        }

        return result;
    }

    public int delete(int id) throws SQLException {
        return super.delete(id, DELETE_DISH);
    }

    @Override
    public int create(Dish dish) throws SQLException {
        int result;

        preparedStatement = getPreparedStatement(CREATE_DISH);
        preparedStatement.setString(FIRST, dish.getName());
        preparedStatement.setString(SECOND, dish.getDescription());
        preparedStatement.setDouble(THIRD, dish.getPrice());

        try {
            result = preparedStatement.executeUpdate();
        } finally {
            closePreparedStatement(preparedStatement);
        }

        return result;
    }

    public Dish findDishByName(String name) throws SQLException {
        Dish dish = null;

        preparedStatement = getPreparedStatement(FIND_DISH_BY_NAME);
        preparedStatement.setString(FIRST, name);

        try(ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                dish = new Dish();
                dish.setId(resultSet.getInt(FIRST));
                dish.setName(resultSet.getString(SECOND));
                dish.setDescription(resultSet.getString(THIRD));
                dish.setPrice(resultSet.getDouble(FOURTH));
            }
        } finally {
            closePreparedStatement(preparedStatement);
        }

        return dish;
    }
}
