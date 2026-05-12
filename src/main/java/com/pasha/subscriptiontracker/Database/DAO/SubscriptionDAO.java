package com.pasha.subscriptiontracker.Database.DAO;

import com.pasha.subscriptiontracker.Database.ConnectionDatabase;
import com.pasha.subscriptiontracker.Model.Subscription.Subscription;
import com.pasha.subscriptiontracker.Model.enums.BillingCycle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionDAO {
    // Add new subscription
    public boolean add(Subscription s) {
        Connection con = ConnectionDatabase.Connect();
        if (con == null) {
            System.out.println("Can not connect to the Database");
            return false;
        }

        String sql = "INSERT INTO subscriptions (user_id, category_id, currency_id, name, website, cost, billing_cycle, next_payment) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, s.getUserId());
            stmt.setInt(2, s.getCategoryId());
            stmt.setInt(3, s.getCurrencyId());
            stmt.setString(4, s.getName());
            stmt.setString(5, s.getWebsite());
            stmt.setDouble(6, s.getCost());
            stmt.setString(7, s.getBillingCycle().name().toLowerCase());
            stmt.setDate(8, java.sql.Date.valueOf(s.getNextPayment()));

            int lines = stmt.executeUpdate();
            if (lines > 0) {
                System.out.println("Subscription added");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error in SubscriptionDAO: " + e.getMessage());
        }
        return false;
    }

    // Get user's subscriptions
    public List<Subscription> getByUserId(int userId) {
        Connection con = ConnectionDatabase.Connect();
        if (con == null) {
            System.out.println("Can not connect to the Database");
            return null;
        }

        String sql = "SELECT * FROM subscriptions WHERE user_id = ?";

        List<Subscription> list = new ArrayList<>();
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Something went wrong in SubscriptionDAO getByUserId: " + e.getMessage());;
        }
        return null;
    }

    // Update subscription
    public boolean update(Subscription s) {
        Connection con = ConnectionDatabase.Connect();
        if (con == null) {
            System.out.println("Can not connect to the Database");
            return false;
        }

        String sql = "UPDATE subscriptions SET user_id=?, category_id=?, currency_id=?, name=?, website=?, cost=?, billing_cycle=?, next_payment=? WHERE id=?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, s.getUserId());
            stmt.setInt(2, s.getCategoryId());
            stmt.setInt(3, s.getCurrencyId());
            stmt.setString(4, s.getName());
            stmt.setString(5, s.getWebsite());
            stmt.setDouble(6, s.getCost());
            stmt.setString(7, s.getBillingCycle().name().toLowerCase());
            stmt.setDate(8, java.sql.Date.valueOf(s.getNextPayment()));
            stmt.setInt(9, s.getId());

            int lines = stmt.executeUpdate();
            if (lines > 0) {
                System.out.println("Updated subscription");
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Something went wrong in SubsriptionDAO update: " + e.getMessage());
        }
        return false;
    }

    // Delete subscription
    public boolean delete(int id) {
        Connection con = ConnectionDatabase.Connect();
        if (con == null) {
            System.out.println("Can not connect to the Database");
            return false;
        }

        String sql = "DELETE FROM subscriptions WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);

            int lines = stmt.executeUpdate();
            if (lines > 0) {
                System.out.println("Subscription deleted");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong in SubscriptionDAO delete: " + e.getMessage());
        }
        return false;
    }

    // Get upcoming subscription from now
    public List<Subscription> getUpcoming(int userId, int days) {
        Connection con = ConnectionDatabase.Connect();
        if (con == null) {
            System.out.println("Can not connect to the Database");
            return null;
        }

        String sql = "SELECT * FROM subscriptions WHERE user_id = ? AND next_payment BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL ? DAY)";
        List<Subscription> list = new ArrayList<>();
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, days);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
            return list;

        } catch (SQLException e) {
            System.out.println("Something went wrong in SubscriptionDAO getUpcoming: " + e.getMessage());
        }
        return null;
    }

    // Mapping a row with a new subscription (helper function)
    private Subscription mapRow(ResultSet rs) throws SQLException {
        Subscription s = new Subscription();
        s.setId(rs.getInt("id"));
        s.setUserId(rs.getInt("user_id"));
        s.setCategoryId(rs.getInt("category_id"));
        s.setCurrencyId(rs.getInt("currency_id"));
        s.setName(rs.getString("name"));
        s.setWebsite(rs.getString("website"));
        s.setCost(rs.getDouble("cost"));
        s.setBillingCycle(BillingCycle.valueOf(rs.getString("billing_cycle").toUpperCase()));
        s.setNextPayment(rs.getDate("next_payment").toLocalDate());
        return s;
    }
}
