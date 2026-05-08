package com.pasha.subscriptiontracker.Database.DAO;

import com.pasha.subscriptiontracker.Database.ConnectionDatabase;
import com.pasha.subscriptiontracker.Model.enums.Role;
import com.pasha.subscriptiontracker.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDAO {
    // Register new user
    public boolean register(User user) {
        // Connecting to the database
        Connection con = ConnectionDatabase.Connect();
        if (con == null) {
            System.out.println("Can not connect to the Database");
            return false;
        }
        // Checking if user with the same name already exists
        if (userExists(user.getUsername())) {
            System.out.println("User with that username already exists");
            return false;
        }

        String sql = "INSERT INTO users (username, password, email, role, team_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getRole().name().toLowerCase());
            stmt.setInt(5, user.getTeamId());

            int lines = stmt.executeUpdate();
            if (lines > 0) {
                System.out.println("User Registered");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error in UsersDAO: " + e.getMessage());
        }
        return false;
    }

    // Login
    public User login(String username, String password) {
        Connection con = ConnectionDatabase.Connect();
        if (con == null) return null;

        String sql = "SELECT * FROM users where username = ? AND password = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setRole(Role.valueOf(rs.getString("role").toUpperCase()));
                user.setTeamId(rs.getInt("team_id"));
                return user;
            }

        } catch (SQLException e) {
            System.out.println("Error in UsersDAO: " + e.getMessage());
        }
        return null;
    }

    // Check if the User exist
    public boolean userExists(String username) {
        Connection con = ConnectionDatabase.Connect();
        if (con == null) return false;

        String sql = "SELECT COUNT(*) FROM users where username = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            // Checking if there is one user or more
            if (rs.next()) return rs.getInt(1) > 0;
        } catch (SQLException e) {
            System.out.println("Error in UsersDAO: " + e.getMessage());
        }
        return false;
    }
}
