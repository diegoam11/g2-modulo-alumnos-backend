package com.fisiunmsm.ayudoc_alumnos.application.service;

import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.NotaResponse;
import reactor.core.publisher.Mono;

public interface CalificarService {
    Mono<NotaResponse> calificarNota(Long alumnoId, Long cursoId, Long componentenotaId, Double nuevaNota);
    Mono<NotaResponse> crearNota(Long alumnoId, Long cursoId, Long componentenotaId, Double nuevaNota);
}
