package com.fisiunmsm.ayudoc_alumnos.presentation.controller;

import com.fisiunmsm.ayudoc_alumnos.application.service.AlumnoService;
import com.fisiunmsm.ayudoc_alumnos.domain.model.Alumno;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("api-alumno/v1/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;

    @GetMapping()
    public Flux<Alumno> findAll() {
        return alumnoService.findAll();
    }

    @GetMapping("{id}")
    public Mono<Alumno> findById(@PathVariable Long id) {
        return alumnoService.findById(id);
    }

}
