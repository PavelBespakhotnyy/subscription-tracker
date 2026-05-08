package com.pasha.subscriptiontracker.Model;

public class Notification {
    private Integer id;
    private Integer subscriptionId;
    private Integer notifyBefore;
    private Boolean isEnabled;

    public Notification() {
    }

    public Notification(Integer id, Integer subscriptionId, Integer notifyBefore, Boolean isEnabled) {
        this.id = id;
        this.subscriptionId = subscriptionId;
        this.notifyBefore = notifyBefore;
        this.isEnabled = isEnabled;
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

    public Integer getNotifyBefore() {
        return notifyBefore;
    }

    public void setNotifyBefore(Integer notifyBefore) {
        this.notifyBefore = notifyBefore;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }
}
