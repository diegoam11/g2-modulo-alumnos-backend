package com.fisiunmsm.ayudoc_alumnos.application.service;

import com.fisiunmsm.ayudoc_alumnos.domain.model.Alumno;
import com.fisiunmsm.ayudoc_alumnos.domain.model.AlumnoInfoDTO;
import com.fisiunmsm.ayudoc_alumnos.domain.model.infoAca.AlumnoInfo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AlumnoService {
    Flux<Alumno> findAll();
    Mono<Alumno> findById(Long id);
    Mono<AlumnoInfoDTO> findAlumnoByUsername(String username);
    Mono<Long> findIdByUsername(String username);
    Mono<AlumnoInfo> getInfoAcademica(Long alumnoId);
}
