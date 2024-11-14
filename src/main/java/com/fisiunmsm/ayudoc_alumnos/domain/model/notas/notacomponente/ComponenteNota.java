package com.fisiunmsm.ayudoc_alumnos.domain.model.notas.notacomponente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ComponenteNota {
    private Long componentenotaid;
    private Double nota;
    private String nombreComponente;
    private Long padreId;
    private Boolean calculado;
    private Long formulaId;
}
