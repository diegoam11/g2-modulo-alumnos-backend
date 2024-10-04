package com.fisiunmsm.ayudoc_alumnos.application.service;

import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.inscripcion.AlumnoGrupoTable;
import reactor.core.publisher.Mono;

public interface AlumnoGrupoService {
    Mono<AlumnoGrupoTable> registrarAlumnoEnGrupo(Long alumnoid, Long cursoid, Long grupoid);
}
