package com.fisiunmsm.ayudoc_alumnos.domain.model.notas.notacomponente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
    @Getter
    @Setter
    @RequiredArgsConstructor
    @AllArgsConstructor
public class NotaResponse {
    private Long cursoId;
    private Long alumnoId;
    private List<ComponenteNota> notas;
}
