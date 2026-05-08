package com.pasha.subscriptiontracker.Model;

import java.time.LocalDateTime;

public class PaymentHistory {
    private Integer id;
    private Integer subscriptionId;
    private Double amount;
    private LocalDateTime paymentDate;

    public PaymentHistory() {
    }

    public PaymentHistory(Integer id, Integer subscriptionId, Double amount, LocalDateTime paymentDate) {
        this.id = id;
        this.subscriptionId = subscriptionId;
        this.amount = amount;
        this.paymentDate = paymentDate;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
}
