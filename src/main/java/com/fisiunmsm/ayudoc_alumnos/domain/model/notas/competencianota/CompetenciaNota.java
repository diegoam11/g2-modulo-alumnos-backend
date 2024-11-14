package com.fisiunmsm.ayudoc_alumnos.domain.model.notas.competencianota;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class CompetenciaNota {
    private String competenciaid;
    private String competenciacodigo;
    private String competencianombre;
    private String competenciadescripcion;
    private String competenciatipo;
    private Long notaid;
    private Double nota;
    private Long componentenotaid;
    private String componentecodigo;
    private String componentedescripcion;
}