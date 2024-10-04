package com.fisiunmsm.ayudoc_alumnos.application.service;

import com.fisiunmsm.ayudoc_alumnos.domain.model.AlumnoCurso;
import com.fisiunmsm.ayudoc_alumnos.domain.model.AlumnoCursoDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AlumnoCursoService {
    Flux<AlumnoCurso> findAll();
    Mono<AlumnoCurso> findById(Long id);
    Flux<AlumnoCurso> findByEstado(String estado);
    Flux<AlumnoCursoDTO> getCursosByAlumnoId(Long alumnoId);
}
