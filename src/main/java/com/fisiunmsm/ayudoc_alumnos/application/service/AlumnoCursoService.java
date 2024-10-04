package com.fisiunmsm.ayudoc_alumnos.application.service;

import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.inscripcion.AlumnoCursoTable;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.inscripcion.DTO.AlumnoCursoRequest;
import reactor.core.publisher.Mono;

public interface AlumnoCursoService {
    Mono<AlumnoCursoTable> agregarAlumnoACurso(AlumnoCursoRequest request);
}
