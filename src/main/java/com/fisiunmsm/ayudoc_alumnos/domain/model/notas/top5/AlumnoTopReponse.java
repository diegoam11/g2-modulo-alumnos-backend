package com.fisiunmsm.ayudoc_alumnos.domain.model.notas.top5;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoTopReponse {
    private Long alumnoid;
    private Long cursoid;
    private Long componentenotaid;
    private Long notaid;
    private Double nota;
    private String nombres;
    private String apellidos;
    private String codigoalumno;
    private String descripcion;
}
