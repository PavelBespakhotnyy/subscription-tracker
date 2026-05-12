package com.pasha.subscriptiontracker.Model;

import com.pasha.subscriptiontracker.Model.enums.SubscriptionRequestType;

import java.time.LocalDateTime;

public class SubscriptionRequest {
    private Integer id;
    private Integer userId;
    private String subscriptionName;
    private SubscriptionRequestType status;
    private LocalDateTime createdAt;

    public SubscriptionRequest() {
    }

    public SubscriptionRequest(Integer id, Integer userId, String subscriptionName, SubscriptionRequestType status, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.subscriptionName = subscriptionName;
        this.status = status;
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

    public String getSubscriptionName() {
        return subscriptionName;
    }

    public void setSubscriptionName(String subscriptionName) {
        this.subscriptionName = subscriptionName;
    }

    public SubscriptionRequestType getStatus() {
        return status;
    }

    public void setStatus(SubscriptionRequestType status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
