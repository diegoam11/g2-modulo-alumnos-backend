package com.fisiunmsm.ayudoc_alumnos.application.service;

import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.competencianota.NotasDTO;
import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.top5.AlumnoTopReponse;
import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.notacomponente.NotaResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AlumnoNotaService {
    Mono<NotaResponse> getNotasDeAlumnoEnCurso(Long alumnoId, Long cursoId);
    Flux<AlumnoTopReponse> getTop5NotasAlumnoPorComponente(Long componenteId, Long cursoId);
    Flux<NotasDTO> obtenerNotasPorCompetenciaCursoAlumno(Long competenciaId, Long cursoId, Long alumnoId);
}
