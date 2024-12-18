package com.fisiunmsm.ayudoc_alumnos.application.service;

import com.fisiunmsm.ayudoc_alumnos.domain.model.infoAca.AlumnoNotasFinal;
import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.competencianota.CompetenciaNotaDTO;
import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.notacomponente.AlumnoNota;
import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.top5.AlumnoTopReponse;
import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.notacomponente.NotaResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AlumnoNotaService {
    Mono<NotaResponse> getNotasDeAlumnoEnCurso(Long alumnoId, Long cursoId);
    Flux<AlumnoTopReponse> getTop5NotasAlumnoPorComponente(Long componenteId, Long cursoId);
    Flux<AlumnoNotasFinal> obtenerNotasAprobadasConPeriodo(Long alumnoId);
    Flux<CompetenciaNotaDTO> findNotasCompetenciaByAlumnoAndCurso(Long cursoId, Long alumnoId);
    Flux<AlumnoNota> findNotasEvaluaciones (Long cursoId);
}
