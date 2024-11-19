package com.fisiunmsm.ayudoc_alumnos.domain.model.notas.competencianota;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class CompetenciaNotaDTO {
    private Long cursocompetenciaid;
    private Long competenciaid;
    private String nombre;
    private String competenciadescripcion;
    private String tipo;
    private Long cursoid;
    private String componentecodigo;
    private String componentedescripcion;
    private String componentepeso;
    private Double nota;
}
