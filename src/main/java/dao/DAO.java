package dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T extends Object> {
    T get(int id);
    List<T> list();
    int save(T obj) throws SQLException;
    void delete(int id) throws SQLException;
}