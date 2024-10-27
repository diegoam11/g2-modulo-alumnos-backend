package com.fisiunmsm.ayudoc_alumnos.application.serviceImpl;

import com.fisiunmsm.ayudoc_alumnos.application.dtos.CreateNotificationRq;
import com.fisiunmsm.ayudoc_alumnos.application.service.NotificationService;
import com.fisiunmsm.ayudoc_alumnos.domain.model.Notification;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.NotificationTable;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.sql.Date;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationServiceImp implements NotificationService {
    private final NotificationRepository notificationRepository;
    
    @Override
    public Mono<Notification> createNotification(CreateNotificationRq rq) {
        Notification notification = new Notification();
        
        notification.setAlumnoId(rq.getAlumnoId());
        notification.setCursoId(rq.getCursoId());
        notification.setFecha(Date.valueOf(LocalDateTime.now().toLocalDate()));
        
        if ("Calificacion".equalsIgnoreCase(rq.getTipo())) {
            notification.setMensaje("Tienes un nueva calificación en el curso" + rq.getCursoId());
        }
        
        if ("Anuncio".equalsIgnoreCase(rq.getTipo())) {
            notification.setMensaje("Tienes un nuevo anuncio en el curso" + rq.getCursoId());
        }
        
        NotificationTable notificationTable = NotificationTable.fromDomainModel(notification);
        return notificationRepository.save(notificationTable).flatMap(NotificationTable::toMono);
    }
}
