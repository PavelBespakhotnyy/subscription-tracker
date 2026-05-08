package com.pasha.subscriptiontracker.Model;

import java.time.LocalDateTime;

public class AuditLog {
    private Integer id;
    private Integer userId;
    private String action;
    private String entity;
    private Integer entityId;
    private LocalDateTime createdAt;

    public AuditLog() {
    }

    public AuditLog(Integer id, Integer userId, String action, String entity, Integer entityId, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.action = action;
        this.entity = entity;
        this.entityId = entityId;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
