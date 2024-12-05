package com.fisiunmsm.ayudoc_alumnos.domain.model.notas.competencianota;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class RankingDTO {
    private Long alumnoid;
    private String nombres;
    private String apellidos;
    private String alumnocodigo;
    private Long cursocompetenciaid;
    private Long competenciaid;
    private Long cursoid;
    private String competenciacodigo;
    private String nombrecompetencia;
    private String competenciadescripcion;
    private Double componentepeso;
    private Double nota;
}
