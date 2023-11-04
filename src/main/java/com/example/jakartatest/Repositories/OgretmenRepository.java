package com.example.jakartatest.Repositories;


import com.example.jakartatest.Entities.Ogretmen;
import com.example.jakartatest.constants.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OgretmenRepository implements BaseRepository<Ogretmen> {

    @Override
    public List<Ogretmen> getAll() throws SQLException {
        ArrayList<Ogretmen> list = new ArrayList<>();
        Connection connection = DbConnection.connect();
        Statement stm = connection.createStatement();
        ResultSet result = stm.executeQuery("SELECT * FROM \"OGRETMEN\" ORDER BY \"ID\" ASC");

        while (result.next()) {
            list.add(new Ogretmen(result.getLong("ID"), result.getString("NAME"), result.getBoolean("IS_GICIK")));
        }
        connection.close();
        stm.close();
        result.close();

        return list;
    }

    @Override
    public Ogretmen getById(long id) throws SQLException {
        Ogretmen ogretmen = null;

        Connection connection = DbConnection.connect();
        String sql = "SELECT * FROM \"OGRETMEN\" WHERE \"ID\" = ?";
        PreparedStatement stm = connection.prepareStatement(sql);

        stm.setLong(1, id);
        ResultSet result = stm.executeQuery();
        while (result.next()) {
            ogretmen = new Ogretmen(result.getLong("ID"), result.getString("NAME"), result.getBoolean("IS_GICIK"));
        }
        return ogretmen;
    }

    @Override
    public boolean save(Ogretmen ogretmen) throws SQLException {
        boolean result = false;

        Connection connection = DbConnection.connect();
        String sql = "INSERT INTO \"OGRETMEN\"(\"NAME\", \"IS_GICIK\") VALUES(?, ?)";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, ogretmen.getName());
        stm.setBoolean(2, ogretmen.isGıcık());

        result = stm.executeUpdate() == 1;
        connection.close();
        stm.close();

        return result;
    }

    @Override
    public boolean deleteById(long id) throws SQLException {
        boolean result = false;
        Connection connection = DbConnection.connect();
        String sql = "DELETE FROM \"OGRETMEN\" WHERE \"ID\" = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setLong(1, id);

        result = stm.executeUpdate() == 1;
        connection.close();
        stm.close();

        return result;
    }
}
