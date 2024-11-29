package com.fisiunmsm.ayudoc_alumnos.domain.model.inscripcionCurso;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GrupoResponse {
    private Long id;
    private String codigo;
    private String estado;
    private Long cursoId;
}
