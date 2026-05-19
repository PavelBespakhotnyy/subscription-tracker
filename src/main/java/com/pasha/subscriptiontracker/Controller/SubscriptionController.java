package com.pasha.subscriptiontracker.Controller;

import com.pasha.subscriptiontracker.Database.DAO.*;
import com.pasha.subscriptiontracker.Model.AuditLog;
import com.pasha.subscriptiontracker.Model.Currency;
import com.pasha.subscriptiontracker.Model.Notification;
import com.pasha.subscriptiontracker.Model.PaymentHistory;
import com.pasha.subscriptiontracker.Model.Subscription.Subscription;
import com.pasha.subscriptiontracker.Model.Subscription.SubscriptionRequest;
import com.pasha.subscriptiontracker.Model.Subscription.SubscriptionStatus;
import com.pasha.subscriptiontracker.Model.enums.BillingCycle;
import com.pasha.subscriptiontracker.Model.enums.SubscriptionStatusType;

import java.util.ArrayList;
import java.util.List;

public class SubscriptionController {
    SubscriptionDAO subscriptionDAO = new SubscriptionDAO();
    SubscriptionRequestDAO subscriptionRequestDAO = new SubscriptionRequestDAO();
    SubscriptionStatusDAO subscriptionStatusDAO = new SubscriptionStatusDAO();
    AuditLogDAO auditLogDAO = new AuditLogDAO();
    CurrencyDAO currencyDAO = new CurrencyDAO();
    PaymentHistoryDAO paymentHistoryDAO = new PaymentHistoryDAO();
    NotificationDAO notificationDAO = new NotificationDAO();

    // SubscriptionDAO methods
    public boolean addSubscription(Subscription s) {
        boolean result = subscriptionDAO.add(s);
        if (result) {
            // Payment History
            PaymentHistory p = new PaymentHistory();
            p.setSubscriptionId(s.getId());
            p.setAmount(s.getCost());
            p.setPaymentDate(s.getNextPayment());
            paymentHistoryDAO.add(p);

            // Default status
            SubscriptionStatus st = new SubscriptionStatus();
            st.setSubscriptionId(s.getId());
            st.setStatus(SubscriptionStatusType.ACTIVE);
            subscriptionStatusDAO.add(st);

            // Default notification
            Notification n = new Notification();
            n.setSubscriptionId(s.getId());
            n.setNotifyBefore(3);
            n.setEnabled(true);
            notificationDAO.add(n);

            // Audit log
            AuditLog log = new AuditLog();
            log.setUserId(s.getUserId());
            log.setAction("added subscription");
            log.setEntity("subscriptions");
            log.setEntityId(s.getId());
            auditLogDAO.add(log);
        }
        return result;
    }   // Add new subscription, audit log and notification

    public List<Subscription> getByUserId(int userId) {
        return subscriptionDAO.getByUserId(userId);
    }

    public boolean update(Subscription s) {
        boolean result = subscriptionDAO.update(s);
        if (result) {
            AuditLog log = new AuditLog();
            log.setUserId(s.getUserId());
            log.setAction("updated subscription");
            log.setEntity("subscriptions");
            log.setEntityId(s.getId());
            auditLogDAO.add(log);
        }
        return result;
    }   // Update subscription and create audit log

    public boolean delete(int id, int userId) {
        boolean result = subscriptionDAO.delete(id);
        if (result) {
            AuditLog log = new AuditLog();
            log.setUserId(userId);
            log.setAction("deleted subscription");
            log.setEntity("subscriptions");
            log.setEntityId(id);
            auditLogDAO.add(log);
        }
        return result;
    }   // Delete Subscription and create audit log

    public List<Subscription> getUpcoming(int userId, int days) {
        return subscriptionDAO.getUpcoming(userId, days);
    }

    // Upcoming subscriptions with enabled notifications
    public List<Subscription> getUpcomingForNotifications(int userId) {
        List<Subscription> result = new ArrayList<>();
        List<Subscription> upcoming = subscriptionDAO.getUpcoming(userId, 7);
        for (Subscription s : upcoming) {
            Notification n = notificationDAO.getBySubscriptionId(s.getId());
            if (n != null && n.getEnabled()) {
                result.add(s);
            }
        }
        return result;
    }

    // Notifications methods
    // Update notifiaction
    public boolean updateNotification(Notification n) {
        return notificationDAO.update(n);
    }

    // SubscriptionRequestDAO methods
    public boolean addRequest(SubscriptionRequest r) {
        return subscriptionRequestDAO.add(r);
    }

    public List<SubscriptionRequest> getByTeamId(int teamId) {
        return subscriptionRequestDAO.getByTeamId(teamId);
    }

    public boolean updateRequest(SubscriptionRequest r) {
        return subscriptionRequestDAO.update(r);
    }

    // SubscriptionStatusDAO methods
    public boolean addStatus(SubscriptionStatus st) {
        return subscriptionStatusDAO.add(st);
    }

    public SubscriptionStatus getBySubscriptionId(int subscriptionId) {
        return subscriptionStatusDAO.getBySubscriptionId(subscriptionId);
    }

    // CurrencyDAO methods
    public List<Currency> getAllCurrencies() {
        return currencyDAO.getAll();
    }

    // PaymentHistoryDAO methods
    public List<PaymentHistory> getPaymentHistory(int subscriptionId) {
        return paymentHistoryDAO.getBySubscriptionId(subscriptionId);
    }

    // Get total cost of all monthly subscriptions
    public double getTotalMonthlyCost(int userId) {
        List<Subscription> list = subscriptionDAO.getByUserId(userId);
        double total = 0;
        for (Subscription s : list) {
            if (s.getBillingCycle() == BillingCycle.MONTHLY) {
                total += s.getCost();
            } else {
                total += s.getCost() / 12;
            }
        }
        return total;
    }

    // Pause or Resume subscription
    public boolean changeStatus(int subscriptionId, SubscriptionStatusType newStatus, int userId) {
        SubscriptionStatus st = new SubscriptionStatus();
        st.setSubscriptionId(subscriptionId);
        st.setStatus(newStatus);
        boolean result = subscriptionStatusDAO.add(st);
        if (result) logAction(userId, "changed status to " + newStatus.name().toLowerCase(), subscriptionId);
        return result;
    }

    // Logging
    private void logAction(int userId, String action, int entityId) {
        AuditLog log = new AuditLog();
        log.setUserId(userId);
        log.setAction(action);
        log.setEntity("subscriptions");
        log.setEntityId(entityId);
        auditLogDAO.add(log);
    }
}
