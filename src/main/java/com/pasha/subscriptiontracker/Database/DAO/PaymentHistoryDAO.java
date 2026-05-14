package com.pasha.subscriptiontracker.Database.DAO;

import com.pasha.subscriptiontracker.Database.ConnectionDatabase;
import com.pasha.subscriptiontracker.Model.PaymentHistory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentHistoryDAO {
    // Add payment
    public boolean add(PaymentHistory pmtHst) {
        Connection con = ConnectionDatabase.Connect();
        if (con == null) {
            System.out.println("Can not connect to the Database");
            return false;
        }

        String sql = "INSERT INTO payment_history (subscription_id, amount, payment_date) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, pmtHst.getSubscriptionId());
            stmt.setDouble(2, pmtHst.getAmount());
            stmt.setDate(3, java.sql.Date.valueOf(pmtHst.getPaymentDate()));

            int lines = stmt.executeUpdate();
            if (lines > 0) {
                System.out.println("Payment added in history");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong in PaymentHistoryDAO add: " + e.getMessage());
        }
        return false;
    }

    // Returns list of payment history of certain subscription
    public List<PaymentHistory> getBySubscriptionId(int subscriptionId) {
        Connection con = ConnectionDatabase.Connect();
        if (con == null) {
            System.out.println("Can not connect to the Database");
            return null;
        }

        String sql = "SELECT * FROM payment_history WHERE subscription_id = ?";
        List<PaymentHistory> list = new ArrayList<>();
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, subscriptionId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Something went wrong in PaymentHistoryDAO getBySubscriptionId: " + e.getMessage());
        }
        return null;
    }

    // Mapping the row to create a payment history object to pass it in the list
    private PaymentHistory mapRow(ResultSet rs) throws SQLException {
        PaymentHistory p = new PaymentHistory();
        p.setId(rs.getInt("id"));
        p.setSubscriptionId(rs.getInt("subscription_id"));
        p.setAmount(rs.getDouble("amount"));
        p.setPaymentDate(rs.getDate("payment_date").toLocalDate());
        return p;
    }
}
