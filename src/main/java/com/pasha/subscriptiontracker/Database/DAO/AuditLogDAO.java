package com.pasha.subscriptiontracker.Database.DAO;

import com.pasha.subscriptiontracker.Database.ConnectionDatabase;
import com.pasha.subscriptiontracker.Model.AuditLog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuditLogDAO {
    // Add audit log
    public boolean add(AuditLog al) {
        Connection con = ConnectionDatabase.Connect();
        if (con == null) {
            System.out.println("Can not connect to the Database");
            return false;
        }

        String sql = "INSERT INTO audit_log (user_id, action, entity, entity_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, al.getUserId());
            stmt.setString(2, al.getAction());
            stmt.setString(3, al.getEntity());
            stmt.setInt(4, al.getEntityId());

            int lines = stmt.executeUpdate();
            if (lines > 0) {
                System.out.println("Audit log added");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Someting went wrong in AuditLogDAO add: " + e.getMessage());
        }
        return false;
    }

    // Getting logs from userId
    public List<AuditLog> getUserById(int userId) {
        Connection con = ConnectionDatabase.Connect();
        if (con == null) {
            System.out.println("Can not connect to the Database");
            return null;
        }

        String sql = "SELECT * FROM audit_log WHERE user_id = ?";
        List<AuditLog> list = new ArrayList<>();
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Something went wrong in AuditLogDAO getUserById: " + e.getMessage());
        }
        return null;
    }

    // Mapping the row to reutrn object AuditLog
    private AuditLog mapRow(ResultSet rs) throws SQLException {
        AuditLog al = new AuditLog();
        al.setId(rs.getInt("id"));
        al.setUserId(rs.getInt("user_id"));
        al.setAction(rs.getString("action"));
        al.setEntity(rs.getString("entity"));
        al.setEntityId(rs.getInt("entity_id"));
        al.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        return al;
    }
}
