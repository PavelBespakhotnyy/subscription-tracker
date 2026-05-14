package com.pasha.subscriptiontracker.Database.DAO;

import com.pasha.subscriptiontracker.Database.ConnectionDatabase;
import com.pasha.subscriptiontracker.Model.Currency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CurrencyDAO {
    // Getting all currencies
    public List<Currency> getAll() {
        Connection con = ConnectionDatabase.Connect();
        if (con == null) return null;

        String sql = "SELECT * FROM currencies";
        List<Currency> list = new ArrayList<>();
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Something went wrong in CurrencyDAO getAll: " + e.getMessage());
        }
        return null;
    }

    // Getting currency by id
    public Currency getById(int id) {
        Connection con = ConnectionDatabase.Connect();
        if (con == null) {
            System.out.println("Can not connect to the Database");
            return null;
        }

        String sql = "SELECT * FROM currencies WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapRow(rs);
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong in CurrencyDAO getById: " + e.getMessage());
        }
        return null;
    }

    // Mapping the row and return object currency
    private Currency mapRow(ResultSet rs) throws SQLException {
        Currency c = new Currency();
        c.setId(rs.getInt("id"));
        c.setCode(rs.getString("code"));
        c.setSymbol(rs.getString("symbol"));
        return c;
    }
}
