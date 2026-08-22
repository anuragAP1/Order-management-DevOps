package com.piyush.ordermanagement.notification_service.service;

import com.piyush.ordermanagement.notification_service.model.Notification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class NotificationService {

    private final Map<Long, Notification> notificationStore = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    public List<Notification> getAllNotifications() {
        return new ArrayList<>(notificationStore.values());
    }

    public Notification createNotification(String message) {
        long id = idCounter.incrementAndGet();
        Notification notification = new Notification(id, message, LocalDateTime.now());
        notificationStore.put(id, notification);

        // Simulated email send
        System.out.println("[NOTIFICATION] " + message);

        return notification;
    }
}