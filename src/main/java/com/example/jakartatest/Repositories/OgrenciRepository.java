package com.example.jakartatest.Repositories;

import com.example.jakartatest.Entities.Ogrenci;
import com.example.jakartatest.constants.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OgrenciRepository implements BaseRepository<Ogrenci> {

    @Override
    public List<Ogrenci> getAll() throws SQLException {
        ArrayList<Ogrenci> list = new ArrayList<>();
        Connection connection = DbConnection.connect();
        Statement stm = connection.createStatement();
        ResultSet result = stm.executeQuery("SELECT * FROM \"OGRENCI\"");

        while (result.next()) {
            list.add(new Ogrenci(result.getLong("ID"), result.getString("NAME"), result.getLong("OGR_NUMBER"), result.getLong("YEAR")));
        }
        stm.close();
        result.close();
        stm.close();

        return list;
    }

    @Override
    public Ogrenci getById(long id) throws SQLException {
        Ogrenci ogrenci = null;

        Connection connection = DbConnection.connect();
        String sql = "SELECT * FROM \"OGRENCI\" WHERE \"ID\" = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setLong(1, id);
        ResultSet result = stm.executeQuery();
        while (result.next()) {
            ogrenci = new Ogrenci(result.getLong("ID"), result.getString("NAME"), result.getLong("OGR_NUMBER"), result.getLong("YEAR"));
        }

        connection.close();
        stm.close();
        result.close();

        return ogrenci;
    }

    @Override
    public boolean save(Ogrenci ogrenci) throws SQLException {
        boolean result = false;

        Connection connection = DbConnection.connect();
        String sql = "INSERT INTO \"OGRENCI\"(\"NAME\", \"OGR_NUMBER\", \"YEAR\") VALUES(?, ?, ?)";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, ogrenci.getName());
        stm.setLong(2, ogrenci.getOgrNumber());
        stm.setLong(3, ogrenci.getYear());

        result = stm.executeUpdate() == 1;
        connection.close();
        stm.close();

        return result;
    }

    @Override
    public boolean deleteById(long id) throws SQLException {
        boolean result = false;
        Connection connection = DbConnection.connect();
        String sql = "DELETE FROM \"OGRENCI\" WHERE \"ID\" = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setLong(1, id);

        result = stm.executeUpdate() == 1;
        connection.close();
        stm.close();

        return result;
    }
}
