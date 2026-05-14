package com.pasha.subscriptiontracker.Database.DAO;

import com.pasha.subscriptiontracker.Database.ConnectionDatabase;
import com.pasha.subscriptiontracker.Model.Subscription.SubscriptionStatus;
import com.pasha.subscriptiontracker.Model.enums.SubscriptionStatusType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubscriptionStatusDAO {
    // Add subscription status
    public boolean add(SubscriptionStatus st) {
        Connection con = ConnectionDatabase.Connect();
        if (con == null) {
            System.out.println("Can not connect to the Database");
            return false;
        }

        String sql = "INSERT INTO subscription_status (subscription_id, status) VALUES (?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, st.getSubscriptionId());
            stmt.setString(2, st.getStatus().name().toLowerCase());
            int lines = stmt.executeUpdate();
            if (lines > 0) {
                System.out.println("Subscription status added");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong in SubscriptionStatusDAO add: " + e.getMessage());
        }

        return false;
    }

    // Getting Subscription status by subscription id
    public SubscriptionStatus getBySubscriptionId(int subscriptionId) {
        Connection con = ConnectionDatabase.Connect();
        if (con == null) {
            System.out.println("Can not connect to the Database");
            return null;
        }

        String sql = "SELECT * FROM subscription_status WHERE subscription_id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, subscriptionId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapRow(rs);
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong in SubscriptionStatusDAO getBySubscriptionId: " + e.getMessage());
        }
        return null;
    }

    // Mapping the row to create subscriptionStatus
    private SubscriptionStatus mapRow(ResultSet rs) throws SQLException {
        SubscriptionStatus st = new SubscriptionStatus();
        st.setId(rs.getInt("id"));
        st.setSubscriptionId(rs.getInt("subscription_id"));
        st.setStatus(SubscriptionStatusType.valueOf(rs.getString("status").toUpperCase()));
        st.setChangedAt(rs.getTimestamp("changed_at").toLocalDateTime());
        return st;
    }
}
