package com.piyush.ordermanagement.notification_service.controller;

import com.piyush.ordermanagement.notification_service.model.Notification;
import com.piyush.ordermanagement.notification_service.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    @PostMapping
    public ResponseEntity<Notification> createNotification(@RequestBody Map<String, String> body) {
        String message = body.get("message");
        Notification created = notificationService.createNotification(message);
        URI location = URI.create("/api/notifications/" + created.getId());
        return ResponseEntity.created(location).body(created);
    }
}