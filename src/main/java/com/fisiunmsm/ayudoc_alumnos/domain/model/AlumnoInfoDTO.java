package com.fisiunmsm.ayudoc_alumnos.domain.model;

import lombok.Data;

@Data
public class AlumnoInfoDTO extends Alumno{
    private String universidad;
    private String facultad;
    private String plan;
}
