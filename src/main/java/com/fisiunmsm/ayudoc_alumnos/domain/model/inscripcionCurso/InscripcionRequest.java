package com.fisiunmsm.ayudoc_alumnos.domain.model.inscripcionCurso;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InscripcionRequest {
    private Long alumnoid;  // ID del alumno
    private String codigo;   // Código del curso
}
