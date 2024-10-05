package com.fisiunmsm.ayudoc_alumnos.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Notification {
    private Long id;
    private String mensaje;
    private String tipo;
    private Long cursoId;
    private Long alumnoId;
    private Date fecha;
}
