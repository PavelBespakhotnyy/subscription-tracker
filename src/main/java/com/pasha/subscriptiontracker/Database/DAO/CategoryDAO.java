package com.pasha.subscriptiontracker.Database.DAO;

import com.pasha.subscriptiontracker.Database.ConnectionDatabase;
import com.pasha.subscriptiontracker.Model.Category;
import com.pasha.subscriptiontracker.Model.Subscription.Subscription;
import com.pasha.subscriptiontracker.Model.enums.BillingCycle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    // Add category
    public boolean add(Category c) {
        Connection con = ConnectionDatabase.Connect();
        if (con == null) {
            System.out.println("Can not connect to the Database");
            return false;
        }

        String sql = "INSERT INTO categories (user_id, name) VALUES (?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, c.getUserId());
            stmt.setString(2, c.getName());

            int lines = stmt.executeUpdate();
            if (lines > 0) {
                System.out.println("Added new category");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong in CategoryDAO add: " + e.getMessage());
        }
        return false;
    }

    // Getting all categories by user
    public List<Category> getByUserId(int userId) {
        Connection con = ConnectionDatabase.Connect();
        if (con == null) {
            System.out.println("Can not connect to the Database");
            return null;
        }

        String sql = "SELECT * FROM categories WHERE user_id = ?";
        List<Category> list = new ArrayList<>();
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Something went wrong in CategoryDAO getByUserId: " + e.getMessage());
        }
        return  null;
    }

    // Delete category
    public boolean delete(int id) {
        Connection con = ConnectionDatabase.Connect();
        if (con == null) {
            System.out.println("Can not connect to the Database");
            return false;
        }

        String sql = "DELETE FROM categories WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);

            int lines = stmt.executeUpdate();
            if (lines > 0) {
                System.out.println("Category deleted");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong in CategoryDAO delete: " + e.getMessage());
        }
        return false;
    }

    // Mapping the row with all subscriptions
    private Category mapRow(ResultSet rs) throws SQLException {
        Category c = new Category();
        c.setId(rs.getInt("id"));
        c.setUserId(rs.getInt("user_id"));
        c.setName(rs.getString("name"));
        return c;
    }
}
