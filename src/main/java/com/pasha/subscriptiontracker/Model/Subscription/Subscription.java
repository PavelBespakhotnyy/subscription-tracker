package com.pasha.subscriptiontracker.Model.Subscription;

import com.pasha.subscriptiontracker.Model.enums.BillingCycle;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Subscription {
    private Integer id;
    private Integer userId;
    private Integer categoryId;
    private Integer currencyId;
    private String name;
    private String website;
    private Double cost;
    private BillingCycle billingCycle;
    private LocalDate nextPayment;
    private LocalDateTime createdAt;

    public Subscription() {
    }

    public Subscription(Integer id, Integer userId, Integer categoryId, Integer currencyId, String name, String website, Double cost, BillingCycle billingCycle, LocalDate nextPayment, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.categoryId = categoryId;
        this.currencyId = currencyId;
        this.name = name;
        this.website = website;
        this.cost = cost;
        this.billingCycle = billingCycle;
        this.nextPayment = nextPayment;
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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public BillingCycle getBillingCycle() {
        return billingCycle;
    }

    public void setBillingCycle(BillingCycle billingCycle) {
        this.billingCycle = billingCycle;
    }

    public LocalDate getNextPayment() {
        return nextPayment;
    }

    public void setNextPayment(LocalDate nextPayment) {
        this.nextPayment = nextPayment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
