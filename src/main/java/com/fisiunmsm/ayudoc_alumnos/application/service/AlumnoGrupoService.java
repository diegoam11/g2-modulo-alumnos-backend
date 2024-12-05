package com.fisiunmsm.ayudoc_alumnos.application.service;

import com.fisiunmsm.ayudoc_alumnos.domain.model.inscripcionCurso.AlumnoGrupoResponse;
import com.fisiunmsm.ayudoc_alumnos.domain.model.inscripcionCurso.GrupoResponse;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.AlumnoGrupoTable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AlumnoGrupoService {
    Mono<AlumnoGrupoTable> registrarAlumnoEnGrupo(Long alumnoid, Long cursoid, Long grupoid);
    Mono<GrupoResponse> findGrupobyAlumnoIdAndCursoId(Long alumnoid, Long cursoid);
    Flux<AlumnoGrupoResponse> findAlumnosByCursoIdAndGrupoId(Long cursoid, Long grupoid);
    Flux<AlumnoGrupoResponse> findGruposByCursoId(Long cursoid);
}
