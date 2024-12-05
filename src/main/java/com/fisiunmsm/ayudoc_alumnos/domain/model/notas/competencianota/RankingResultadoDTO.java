package com.fisiunmsm.ayudoc_alumnos.domain.model.notas.competencianota;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class RankingResultadoDTO {
    private Long alumnoId;
    private String nombres;
    private String apellidos;
    private String alumnocodigo;
    private Long competenciaid;
    private String competenciacodigo;
    private String nombrecompetencia;
    private Double notaponderada;
}
