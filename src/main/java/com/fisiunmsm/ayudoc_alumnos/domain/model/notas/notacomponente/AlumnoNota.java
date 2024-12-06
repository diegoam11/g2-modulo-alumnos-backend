package com.fisiunmsm.ayudoc_alumnos.domain.model.notas.notacomponente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoNota {
    private Long alumnoid;
    private Long cursoid;
    private String nombre;
    private Long componentenotaid;
    private Double nota;
    private String descripcion;
    private Long padreid;
    private Boolean calculado;
    private Long formulaid;
    private Long nivel;
    private Double peso;
}
