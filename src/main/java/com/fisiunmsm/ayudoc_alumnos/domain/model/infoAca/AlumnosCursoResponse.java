package com.fisiunmsm.ayudoc_alumnos.domain.model.infoAca;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class AlumnosCursoResponse {
    private Long id;
    private String codigo;
    private String nombres;
    private String apellidos;
    private String email;
    private String universidad;
    private String escuela;
}
