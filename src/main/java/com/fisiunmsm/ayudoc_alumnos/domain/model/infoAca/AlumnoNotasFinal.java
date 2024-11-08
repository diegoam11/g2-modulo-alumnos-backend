package com.fisiunmsm.ayudoc_alumnos.domain.model.infoAca;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class AlumnoNotasFinal {
    private Long cursoid;
    private String nombre;
    private Double nota;
    private String ciclo;
    private Integer numcreditos;
    private Long periodoid;
    private String periodocodigo;
    private String periododescripcion;
}
