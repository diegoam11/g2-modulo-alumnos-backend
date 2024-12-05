package com.fisiunmsm.ayudoc_alumnos.application.service;

import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.competencianota.CompetenciaDTO;
import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.competencianota.RankingDTO;
import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.competencianota.RankingResultadoDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CompetenciaService {
    Flux<CompetenciaDTO> obtenerCompetenciasDeCurso(Long cursoId);
    Mono<List<RankingResultadoDTO>> rankingCompetencia(Long cursoId);

}
