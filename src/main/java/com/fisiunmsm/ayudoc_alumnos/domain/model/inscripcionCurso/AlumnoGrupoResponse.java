package com.fisiunmsm.ayudoc_alumnos.domain.model.inscripcionCurso;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AlumnoGrupoResponse {
    private Long alumnoid;
    private String alumnocodigo;
    private String nombres;
    private String apellidos;
    private String email;
    private Long grupoid;
    private String estado;
    private Long cursoid;
}
