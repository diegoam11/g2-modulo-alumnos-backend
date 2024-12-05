package com.fisiunmsm.ayudoc_alumnos.infraestructure.repository;

import com.fisiunmsm.ayudoc_alumnos.domain.model.AlumnoCursoDTO;
import com.fisiunmsm.ayudoc_alumnos.domain.model.infoAca.AlumnosCursoResponse;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.AlumnoCursoTable;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.AlumnoTable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AlumnoCursoRepository extends R2dbcRepository<AlumnoCursoTable, Long> {
    Flux<AlumnoCursoTable> findByEstado(String estado);
    @Query("SELECT ac.alumnoid, c.id as cursoid, c.nombre as nombre, c.codigo as codigo, " +
            "c.tipo as tipo, ac.estado, c.ciclo, c.numCreditos as numcreditos " +
            "FROM alumnocurso ac " +
            "JOIN cursoDecoder c ON ac.cursoid = c.id " +
            "WHERE ac.alumnoid = :alumnoId")
    Flux<AlumnoCursoDTO> findCursosByAlumnoId(@Param("alumnoId") Long alumnoId);

    Mono<AlumnoCursoTable> findByAlumnoidAndCursoid(Long alumnoId, Long cursoId);
    @Query("SELECT a.id, a.codigo, a.nombres, a.apellidos, a.email, i.nombreLargo as universidad, dp.nombre as escuela " +
            "FROM alumnocurso ac " +
            "JOIN alumno a on ac.alumnoid = a.id " +
            "JOIN institucion i on a.institucionid = i.id " +
            "JOIN departamento dp on a.departamentoid = dp.id " +
            "WHERE ac.cursoid=:cursoId " +
            "ORDER BY a.codigo")
    Flux<AlumnosCursoResponse> findAlumnosByCursoid(@Param("cursoId") Long cursoId);
}

