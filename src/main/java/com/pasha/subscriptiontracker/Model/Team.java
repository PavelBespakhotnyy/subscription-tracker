package com.pasha.subscriptiontracker.Model;

import java.time.LocalDateTime;

public class Team {
    private Integer id;
    private String name;
    private String inviteCode;
    private LocalDateTime createdAt;

    public Team() {
    }

    public Team(Integer id, String name, String inviteCode, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.inviteCode = inviteCode;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }
}
