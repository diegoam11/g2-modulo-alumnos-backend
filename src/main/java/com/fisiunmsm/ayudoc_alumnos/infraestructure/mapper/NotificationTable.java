package com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper;

import com.fisiunmsm.ayudoc_alumnos.domain.model.Notification;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import reactor.core.publisher.Mono;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table("notificacion")
public class NotificationTable {
    @Id
    private Long id;
    private String mensaje;
    private String tipo;
    @Column("cursoId")
    private Long cursoId;
    @Column("userId")
    @Nullable
    private Long usuarioId;
    private Date fecha;
    
    public static NotificationTable fromDomainModel(Notification notification){
        return NotificationTable.builder()
                .id(notification.getId())
                .mensaje(notification.getMensaje())
                .tipo(notification.getTipo())
                .cursoId(notification.getCursoId())
                .usuarioId(notification.getAlumnoId())
                .fecha((Date) notification.getFecha())
                .build();
    }
    
    public Notification toDomainModel(){
        return new Notification(
                id, mensaje, tipo, cursoId, usuarioId, fecha);
    }
    
    public Mono<Notification> toMono(){
        return Mono.just(toDomainModel());
    }
    
}
