package com.fisiunmsm.ayudoc_alumnos.presentation.controller;

import com.fisiunmsm.ayudoc_alumnos.application.service.AlumnoService;
import com.fisiunmsm.ayudoc_alumnos.domain.model.Alumno;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("api-alumno/v1/alumnos")
@CrossOrigin(origins = "http://localhost:5173")
public class AlumnoController {

    private final AlumnoService alumnoService;

    @GetMapping()
    public Flux<Alumno> findAll() {
        return alumnoService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Alumno> findById(@PathVariable Long id) {
        return alumnoService.findById(id);
    }

}
