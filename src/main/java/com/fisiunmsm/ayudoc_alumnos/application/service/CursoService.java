package com.fisiunmsm.ayudoc_alumnos.application.service;

import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.CursoTable;
import reactor.core.publisher.Mono;

public interface CursoService {
    Mono<String> generarCodigo(Long idCurso);
    Mono<CursoTable> decodificarCodigoCurso(String codigoCurso);
    Mono<CursoTable> findCursoTableById(Long id);
}
