package com.example.jakartatest.Repositories;


import com.example.jakartatest.Entities.Konu;
import com.example.jakartatest.constants.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KonuRepository implements BaseRepository<Konu> {

    @Override
    public List<Konu> getAll() throws SQLException {
        ArrayList<Konu> list = new ArrayList<>();
        Connection connection = DbConnection.connect();
        Statement stm = connection.createStatement();
        ResultSet result = stm.executeQuery("SELECT * FROM \"KONU\"");

        while (result.next()) {
            list.add(new Konu(result.getLong("ID"), result.getString("NAME")));
        }
        stm.close();
        result.close();
        stm.close();

        return list;
    }

    @Override
    public Konu getById(long id) throws SQLException {
        Konu konu = null;

        Connection connection = DbConnection.connect();
        String sql = "SELECT * FROM \"KONY\" WHERE \"ID\" = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setLong(1, id);
        ResultSet result = stm.executeQuery();
        while (result.next()) {
            konu = new Konu(result.getLong("ID"), result.getString("NAME"));
        }

        connection.close();
        stm.close();
        result.close();

        return konu;
    }

    @Override
    public boolean save(Konu konu) throws SQLException {
        boolean result = false;

        Connection connection = DbConnection.connect();
        String sql = "INSERT INTO \"KONU\"(\"NAME\") VALUES(?)";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, konu.getName());


        result = stm.executeUpdate() == 1;
        connection.close();
        stm.close();

        return result;
    }

    @Override
    public boolean deleteById(long id) throws SQLException {
        return false;
    }
}
