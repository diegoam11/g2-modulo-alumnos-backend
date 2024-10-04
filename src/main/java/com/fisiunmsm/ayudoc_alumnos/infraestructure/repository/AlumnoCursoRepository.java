package com.fisiunmsm.ayudoc_alumnos.infraestructure.repository;

import com.fisiunmsm.ayudoc_alumnos.domain.model.AlumnoCurso;
import com.fisiunmsm.ayudoc_alumnos.domain.model.AlumnoCursoDTO;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;

public interface AlumnoCursoRepository extends R2dbcRepository<AlumnoCurso, Long> {
    Flux<AlumnoCurso> findByEstado(String estado);
    @Query("SELECT ac.alumnoid, c.id as cursoid, c.nombre as nombre, c.codigo as codigo, c.tipo as tipo, ac.estado " +
            "FROM alumnocurso ac " +
            "JOIN curso c ON ac.cursoid = c.id " +
            "WHERE ac.alumnoid = :alumnoId")
    Flux<AlumnoCursoDTO> findCursosByAlumnoId(@Param("alumnoId") Long alumnoId);
}

