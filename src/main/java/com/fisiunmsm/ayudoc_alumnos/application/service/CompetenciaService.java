package com.fisiunmsm.ayudoc_alumnos.application.service;

import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.competencianota.CompetenciaDTO;
import reactor.core.publisher.Flux;

public interface CompetenciaService {
    Flux<CompetenciaDTO> obtenerCompetenciasDeCurso(Long cursoId);
}
