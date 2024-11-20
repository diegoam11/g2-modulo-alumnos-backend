package com.fisiunmsm.ayudoc_alumnos.domain.model.infoAca;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AlumnoDetalles {
    private String codigo;
    private String nombre;
    private String apellidos;
    private String email;
    private String estado;
    private String universidad;
    private String facultad;
}