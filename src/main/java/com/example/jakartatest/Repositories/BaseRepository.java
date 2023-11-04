package com.example.jakartatest.Repositories;

import java.sql.SQLException;
import java.util.List;

public interface BaseRepository<T> {

    List<T> getAll() throws SQLException;

    T getById(long id) throws SQLException;

    boolean save(T t) throws SQLException;

    boolean deleteById(long id) throws SQLException;
}
