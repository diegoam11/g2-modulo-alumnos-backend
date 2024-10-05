package com.fisiunmsm.ayudoc_alumnos.presentation.controller;

import com.fisiunmsm.ayudoc_alumnos.application.dtos.CreateNotificationRq;
import com.fisiunmsm.ayudoc_alumnos.application.service.NotificationService;
import com.fisiunmsm.ayudoc_alumnos.domain.model.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/notifications")
public class NotificationController {
    private final NotificationService notificationService;
    
    @PostMapping("/webhook")
    public Mono<Notification> createNotification(CreateNotificationRq rq) {
        return notificationService.createNotification(rq);
    }
    
}
