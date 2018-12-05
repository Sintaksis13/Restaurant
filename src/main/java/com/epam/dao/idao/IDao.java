package com.epam.dao.idao;

import java.sql.SQLException;
import java.util.List;

public interface IDao<E> {
    List<E> getAll() throws SQLException;
    int create(E entity) throws SQLException;
    int delete(int id, String sqlStatement) throws SQLException;
}
