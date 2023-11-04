package com.example.jakartatest.Repositories;


import com.example.jakartatest.Entities.Ders;
import com.example.jakartatest.Entities.Konu;
import com.example.jakartatest.Entities.Ogretmen;
import com.example.jakartatest.constants.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DersRepository implements BaseRepository<Ders> {
    @Override
    public List<Ders> getAll() throws SQLException {
        ArrayList<Ders> list = new ArrayList<>();

        Connection connection = DbConnection.connect();
        Statement stm = connection.createStatement();

        ResultSet result = stm.executeQuery("select \"DERS\".\"ID\" as \"DERS_ID\", \"OGRETMEN\".\"ID\" as \"OGRETMEN_ID\",\"OGRETMEN\".\"NAME\" as \"OGRETMEN_NAME\",\"OGRETMEN\".\"IS_GICIK\",\n" +
                "\"KONU\".\"ID\" as \"KONU_ID\", \"KONU\".\"NAME\" as \"KONU_NAME\"\n" +
                "from \"DERS\"\n" +
                "join \"KONU\" ON \"KONU\".\"ID\" = \"DERS\".\"KONU_ID\"\n" +
                "join \"OGRETMEN\" ON \"OGRETMEN\".\"ID\" = \"DERS\".\"OGRETMEN_ID\"");

        while (result.next()) {
            Ogretmen ogr = new Ogretmen(result.getLong("OGRETMEN_ID"), result.getString("OGRETMEN_NAME"), result.getBoolean("IS_GICIK"));
            Konu konu = new Konu(result.getLong("KONU_ID"), result.getString("KONU_NAME"));

            list.add(new Ders(result.getLong("DERS_ID"), ogr, konu));
        }

        stm.close();
        connection.close();
        result.close();

        return list;
    }

    @Override
    public Ders getById(long id) throws SQLException {
        Ders ders = null;

        Connection connection = DbConnection.connect();
        String sql = "select \"DERS\".\"ID\" as \"DERS_ID\", \"OGRETMEN\".\"ID\" as \"OGRETMEN_ID\",\"OGRETMEN\".\"NAME\" as \"OGRETMEN_NAME\",\"OGRETMEN\".\"IS_GICIK\",\n" +
                "\"KONU\".\"ID\" as \"KONU_ID\", \"KONU\".\"NAME\" as \"KONU_NAME\" from \"DERS\"\n" +
                "join \"KONU\" ON \"KONU\".\"ID\" = \"DERS\".\"KONU_ID\"\n" +
                "join \"OGRETMEN\" ON \"OGRETMEN\".\"ID\" = \"DERS\".\"OGRETMEN_ID\"\n" +
                "where \"DERS\".\"ID\" = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setLong(1, id);
        ResultSet result = stm.executeQuery();
        while (result.next()) {
            Ogretmen ogr = new Ogretmen(result.getLong("OGRETMEN_ID"), result.getString("OGRETMEN_NAME"), result.getBoolean("IS_GICIK"));
            Konu konu = new Konu(result.getLong("KONU_ID"), result.getString("KONU_NAME"));

            ders = new Ders(result.getLong("DERS_ID"), ogr, konu);
        }

        connection.close();
        stm.close();
        result.close();

        return ders;
    }

    @Override
    public boolean save(Ders ders) throws SQLException {
        boolean result = false;

        Connection connection = DbConnection.connect();
        String sql = "INSERT INTO \"DERS\"(\"OGRETMEN_ID\", \"KONU_ID\") VALUES(?, ?)";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setLong(1, ders.getOgretmen().getId());
        stm.setLong(2, ders.getKonu().getId());

        result = stm.executeUpdate() == 1;
        connection.close();
        stm.close();

        return result;
    }

    @Override
    public boolean deleteById(long id) throws SQLException {
        boolean result = false;

        Connection connection = DbConnection.connect();
        String sql = "DELETE FROM \"DERS\" WHERE \"ID\" = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setLong(1, id);
        result = stm.executeUpdate() == 1;

        connection.close();
        stm.close();

        return result;
    }
}
