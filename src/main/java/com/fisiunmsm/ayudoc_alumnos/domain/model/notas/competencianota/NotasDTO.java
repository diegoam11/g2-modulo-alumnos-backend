package com.fisiunmsm.ayudoc_alumnos.domain.model.notas.competencianota;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class NotasDTO {
    private Long notaid;
    private Long componentenotaid;
    private Double nota;
    private String componentecodigo;
    private String componentedescripcion;
}
