package com.pasha.subscriptiontracker.Database.DAO;

import com.pasha.subscriptiontracker.Database.ConnectionDatabase;
import com.pasha.subscriptiontracker.Model.Notification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NotificationDAO {
    // Add notification
    public boolean add(Notification notification) {
        Connection con = ConnectionDatabase.Connect();
        if (con == null) {
            System.out.println("Can not connect to the Database");
            return false;
        }

        String sql = "INSERT INTO notifications (subscription_id, notify_before, is_enabled) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, notification.getSubscriptionId());
            stmt.setInt(2, notification.getNotifyBefore());
            stmt.setBoolean(3, notification.getEnabled());

            int lines = stmt.executeUpdate();
            if (lines > 0) {
                System.out.println("Notification added");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong in NotificationDAO add: " + e.getMessage());
        }
        return false;
    }

    // Getting subscription by its Id
    public Notification getBySubscriptionId(int subscription_id) {
        Connection con = ConnectionDatabase.Connect();
        if (con == null) {
            System.out.println("Can not connect to the Database");
            return null;
        }

        String sql = "SELECT * FROM notifications WHERE subscription_id = ? ";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, subscription_id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapRow(rs);
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong in NotificationDAO getBySubscriptionId: " + e.getMessage());
        }
        return null;
    }

    public boolean update(Notification n) {
        Connection con = ConnectionDatabase.Connect();
        if (con == null) {
            System.out.println("Can not connect to the Database");
            return false;
        }
        String sql = "UPDATE notifications SET subscription_id = ?, notify_before = ?, is_enabled = ? WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, n.getSubscriptionId());
            stmt.setInt(2, n.getNotifyBefore());
            stmt.setBoolean(3, n.getEnabled());
            stmt.setInt(4, n.getId());

            int lines = stmt.executeUpdate();
            if (lines > 0) {
                System.out.println("Notification updated");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong in NotificationDAO update: " + e.getMessage());
        }
        return false;
    }

    // Mapping the row and returning Notifiaction
    private Notification mapRow(ResultSet rs) throws SQLException {
        Notification n = new Notification();
        n.setId(rs.getInt("id"));
        n.setSubscriptionId(rs.getInt("subscription_id"));
        n.setNotifyBefore(rs.getInt("notify_before"));
        n.setEnabled(rs.getBoolean("is_enabled"));
        return n;
    }
}
