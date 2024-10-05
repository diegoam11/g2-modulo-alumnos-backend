package com.fisiunmsm.ayudoc_alumnos.application.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateNotificationRq {
    private String tipo;
    private Long cursoId;
    private Long alumnoId;
}
