package com.fisiunmsm.ayudoc_alumnos.application.service;

import com.fisiunmsm.ayudoc_alumnos.application.dtos.CreateNotificationRq;
import com.fisiunmsm.ayudoc_alumnos.domain.model.Notification;
import reactor.core.publisher.Mono;

public interface NotificationService {
    Mono<Notification> createNotification(CreateNotificationRq rq);
}
