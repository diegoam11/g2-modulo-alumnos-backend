package com.fisiunmsm.ayudoc_alumnos.domain.model;

import lombok.Data;

@Data
public class AlumnoCursoDTO {
    private Long alumnoid;
    private Long cursoid;
    private String nombre;
    private String codigo;
    private String tipo;
    private String estado;
    private String ciclo;
    private int numcreditos;
}
