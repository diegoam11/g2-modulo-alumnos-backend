package com.fisiunmsm.ayudoc_alumnos.presentation.controller;

import com.fisiunmsm.ayudoc_alumnos.application.service.AlumnoCursoService;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.inscripcion.AlumnoCursoTable;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.inscripcion.DTO.AlumnoCursoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api-alucur/v1")
@RequiredArgsConstructor
public class AlumnoCursoController {
    private final AlumnoCursoService alumnoCursoService;
    @PostMapping("/inscribir")
    public Mono<ResponseEntity<AlumnoCursoTable>> agregarAlumnoACurso(
            @RequestBody AlumnoCursoRequest request) {

        return alumnoCursoService.agregarAlumnoACurso(request)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
}
