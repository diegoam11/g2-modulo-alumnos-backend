package com.fisiunmsm.ayudoc_alumnos.infraestructure.repository;

import com.fisiunmsm.ayudoc_alumnos.domain.model.inscripcionCurso.AlumnoGrupoResponse;
import com.fisiunmsm.ayudoc_alumnos.domain.model.inscripcionCurso.GrupoResponse;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.AlumnoGrupoTable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AlumnoGrupoRepository extends R2dbcRepository<AlumnoGrupoTable,Void> {
    @Query("SELECT g.id, g.codigo, g.estado, ac.cursoid as curso_id " +
            "FROM  alumnogrupo ag " +
            "JOIN alumnocurso ac on ag.alumnocursoid = ac.id " +
            "JOIN grupo g on ag.grupoid = g.id " +
            "WHERE ac.alumnoid = :alumnoId AND ac.cursoid = :cursoId ")
    Mono<GrupoResponse> findGrupoByAlumnoIdAndCursoId(@Param("alumnoId") Long alumnoId,
                                                      @Param("cursoId") Long cursoId);

    @Query("select a.codigo as alumnocodigo, a.nombres, a.apellidos, a.email, a.id as alumnoid, " +
            "ag.grupoid, g.estado, ac.cursoid " +
            "from alumnogrupo ag " +
            "join alumnocurso ac on ag.alumnocursoid = ac.id " +
            "join grupo g on ag.grupoid = g.id " +
            "join alumno a on ac.alumnoid = a.id " +
            "WHERE ac.cursoid = :cursoId AND ag.grupoid = :grupoId ")
    Flux<AlumnoGrupoResponse> findAlumnosByCursoIdAndGrupoId(
    @Param("cursoId") Long cursoId, @Param("grupoId") Long grupoId);

    @Query("select a.id as alumnoid, a.codigo as alumnocodigo, a.nombres, a.apellidos, a.email, " +
            "ag.grupoid, ac.estado, ac.cursoid " +
            "FROM alumnocurso ac " +
            "join alumno a on ac.alumnoid = a.id " +
            "join alumnogrupo ag on ac.id = ag.alumnocursoid " +
            "WHERE ac.cursoid = :cursoId AND ac.estado=1 order by ag.grupoid")
    Flux<AlumnoGrupoResponse> findAllGruposByCursoId(@Param("cursoId") Long cursoId);
}
