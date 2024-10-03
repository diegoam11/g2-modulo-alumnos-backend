package com.fisiunmsm.ayudoc_alumnos.application.service;

import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.AlumnoTopReponse;
import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.NotaResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AlumnoNotaService {
    Mono<NotaResponse> getNotasDeAlumnoEnCurso(Long alumnoId, Long cursoId);
    Flux<AlumnoTopReponse> getTop5NotasAlumnoPorComponente(Long componenteId, Long cursoId);
}
