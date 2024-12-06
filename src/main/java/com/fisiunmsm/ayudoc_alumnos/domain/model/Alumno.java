package com.fisiunmsm.ayudoc_alumnos.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Alumno {
    private Long id;
    private String codigo;
    private String nombres;
    private String apellidos;
    private String email;
    private String estado;
    private Long institucionid;
    private Long departamentoid;
    private Long usuarioId;
}

