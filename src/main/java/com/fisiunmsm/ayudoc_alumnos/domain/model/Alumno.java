package com.fisiunmsm.ayudoc_alumnos.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Alumno {
    private Long id;
    private String codigo;
    private String nombres;
    private String apellidos;
    private String email;
    private String estado;
    private String institucionid;
    private String departamentoid;
    private String usuarioid;
}

