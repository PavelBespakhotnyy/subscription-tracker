package com.pasha.subscriptiontracker.Model.Subscription;

import com.pasha.subscriptiontracker.Model.enums.SubscriptionStatusType;

import java.time.LocalDateTime;

public class SubscriptionStatus {
    private Integer id;
    private Integer subscriptionId;
    private SubscriptionStatusType subscriptionStatusType;
    private LocalDateTime changedAt;

    public SubscriptionStatus() {
    }

    public SubscriptionStatus(Integer id, Integer subscriptionId, SubscriptionStatusType subscriptionStatusType, LocalDateTime changedAt) {
        this.id = id;
        this.subscriptionId = subscriptionId;
        this.subscriptionStatusType = subscriptionStatusType;
        this.changedAt = changedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Integer subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public SubscriptionStatusType getStatus() {
        return subscriptionStatusType;
    }

    public void setStatus(SubscriptionStatusType subscriptionStatusType) {
        this.subscriptionStatusType = subscriptionStatusType;
    }

    public LocalDateTime getChangedAt() {
        return changedAt;
    }

    public void setChangedAt(LocalDateTime changedAt) {
        this.changedAt = changedAt;
    }
}
