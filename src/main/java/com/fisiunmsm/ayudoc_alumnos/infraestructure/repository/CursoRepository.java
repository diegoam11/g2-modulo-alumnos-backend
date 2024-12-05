package com.fisiunmsm.ayudoc_alumnos.infraestructure.repository;


import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.CursoTable;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Mono;


public interface CursoRepository extends R2dbcRepository <CursoTable,Long>{
    Mono<CursoTable> findCursoTableById(@Param("cursoId") Long id);
}
