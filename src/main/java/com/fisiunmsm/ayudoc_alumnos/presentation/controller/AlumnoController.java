package com.fisiunmsm.ayudoc_alumnos.presentation.controller;

import com.fisiunmsm.ayudoc_alumnos.application.service.AlumnoService;
import com.fisiunmsm.ayudoc_alumnos.domain.model.Alumno;
import com.fisiunmsm.ayudoc_alumnos.domain.model.infoAca.AlumnoDetalles;
import com.fisiunmsm.ayudoc_alumnos.domain.model.infoAca.AlumnoInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/alumnos")
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

    @GetMapping("/user/{username}")
    public Mono<Alumno> findByUsername(@PathVariable String username) {
        return alumnoService.findAlumnoByUsername(username);
    }

    @GetMapping("/get-info-academica/{alumnoId}")
    public Mono<AlumnoInfo> getInfoAcademica(@PathVariable Long alumnoId) {
        return alumnoService.getInfoAcademica(alumnoId);
    }
    @GetMapping("/AlumnoDetalles/{username}")
    public Mono<AlumnoDetalles> getInfoAcademicaByUsername(@PathVariable String username) {
        return alumnoService.getInfoAcademicaByUsername(username);
    }
}
