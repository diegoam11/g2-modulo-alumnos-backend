package com.fisiunmsm.ayudoc_alumnos.application.service;

import com.fisiunmsm.ayudoc_alumnos.domain.model.AlumnoCursoDTO;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.AlumnoCursoTable;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.inscripcion.DTO.AlumnoCursoRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AlumnoCursoService {
    Flux<AlumnoCursoTable> findAll();
    Mono<AlumnoCursoTable> findById(Long id);
    Flux<AlumnoCursoTable> findByEstado(String estado);
    Flux<AlumnoCursoDTO> getCursosByAlumnoId(Long alumnoId);
    Mono<AlumnoCursoTable> agregarAlumnoACurso(AlumnoCursoRequest request);

}
