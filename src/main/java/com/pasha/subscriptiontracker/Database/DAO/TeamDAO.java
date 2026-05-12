package com.pasha.subscriptiontracker.Database.DAO;

import com.pasha.subscriptiontracker.Database.ConnectionDatabase;
import com.pasha.subscriptiontracker.Model.Team;
import com.pasha.subscriptiontracker.Model.User;
import com.pasha.subscriptiontracker.Model.enums.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeamDAO {
    // Creating new team
    public boolean create(Team team) {
        Connection con = ConnectionDatabase.Connect();
        if (con == null) {
            System.out.println("Can not connect to the Database");
            return false;
        }

        String sql = "INSERT INTO teams (name, invite_code) VALUES (?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, team.getName());
            stmt.setString(2, team.getInviteCode());

            int lines = stmt.executeUpdate();
            if (lines > 0) {
                System.out.println("Team created");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error in TeamDAO: " + e.getMessage());
        }
        return false;
    }

    // Getting team by invite code
    public Team getByInviteCode(String code) {
        Connection con = ConnectionDatabase.Connect();
        if (con == null) {
            System.out.println("Can not connect to the Database");
            return null;
        }

        String sql = "SELECT * FROM teams WHERE invite_code = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, code);

            ResultSet rs = stmt.executeQuery();if (rs.next()) {
                Team team = new Team();
                team.setId(rs.getInt("id"));
                team.setName(rs.getString("name"));
                team.setInviteCode(rs.getString("invite_code"));
                return team;
            }
        } catch (SQLException e) {
            System.out.println("Error in TeamDAO: " + e.getMessage());
        }
        return null;
    }
}
