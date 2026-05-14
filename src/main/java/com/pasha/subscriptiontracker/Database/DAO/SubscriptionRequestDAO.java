package com.pasha.subscriptiontracker.Database.DAO;

import com.mysql.cj.protocol.Resultset;
import com.pasha.subscriptiontracker.Database.ConnectionDatabase;
import com.pasha.subscriptiontracker.Model.Subscription.SubscriptionRequest;
import com.pasha.subscriptiontracker.Model.enums.SubscriptionRequestType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionRequestDAO {
    // Add subscription request
    public boolean add(SubscriptionRequest r) {
        Connection con = ConnectionDatabase.Connect();
        if (con == null) {
            System.out.println("Can not connect to the Database");
            return false;
        }

        String sql = "INSERT INTO subscription_requests (user_id, subscription_name, status) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, r.getUserId());
            stmt.setString(2,r.getSubscriptionName());
            stmt.setString(3, r.getStatus().name().toLowerCase());

            int lines = stmt.executeUpdate();
            if (lines > 0) {
                System.out.println("Added subscription request");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong in SubscriptionRequestDAO add: " + e.getMessage());
        }

        return false;
    }

    // Getting list of subscription requests by Team Id
    public List<SubscriptionRequest> getByTeamId(int teamId) {
        Connection con = ConnectionDatabase.Connect();
        if (con == null) {
            System.out.println("Can not connect to the Database");
            return null;
        }

        String sql = "SELECT * FROM subscription_requests WHERE id = ?";
        List<SubscriptionRequest> list = new ArrayList<>();
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, teamId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Something went wrong in SubscriptionRequestDAO getByTeamId: " + e.getMessage());
        }
        return null;
    }

    // Updating existing subscription request
    public boolean update(SubscriptionRequest r) {
        Connection con = ConnectionDatabase.Connect();
        if (con == null) {
            System.out.println("Can not connect to the Database");
            return false;
        }

        String sql = "UPDATE subscription_requests subscription_name = ?, status = ? WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, r.getSubscriptionName());
            stmt.setString(2, r.getStatus().name().toLowerCase());
            stmt.setInt(3,r.getId());

            int lines = stmt.executeUpdate();
            if (lines > 0) {
                System.out.println("Subscription request updated");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong in SubscriptionRequestDAO update: " + e.getMessage());
        }
        return false;
    }

    // Mapping the row to create subscriptionRequest
    private SubscriptionRequest mapRow(ResultSet rs) throws SQLException {
        SubscriptionRequest sr = new SubscriptionRequest();
        sr.setId(rs.getInt("id"));
        sr.setUserId(rs.getInt("user_id"));
        sr.setSubscriptionName(rs.getString("subscription_name"));
        sr.setStatus(SubscriptionRequestType.valueOf(rs.getString("status").toUpperCase()));
        sr.setCreatedAt(rs.getTimestamp("requested_at").toLocalDateTime());
        return sr;
    }
}
