package com.pasha.subscriptiontracker.Model;

import com.pasha.subscriptiontracker.Model.enums.Role;

public class User {
    // Attributes
    private Integer Id;
    private String username;
    private String password;
    private String email;
    private Role role;
    private Integer teamId;

    // Constructors
    public User() {
    }

    public User(Integer id, String username, String password, String email, Role role, Integer teamId) {
        Id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.teamId = teamId;
    }

    // Methods
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }
}
