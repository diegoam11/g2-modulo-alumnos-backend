package com.fisiunmsm.ayudoc_alumnos.infraestructure.repository;

import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.inscripcion.AlumnoCursoTable;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface AlumnoCursoRepository extends R2dbcRepository <AlumnoCursoTable,Long> {
    Mono<AlumnoCursoTable> findByAlumnoidAndCursoid(Long alumnoId, Long cursoId);
}
