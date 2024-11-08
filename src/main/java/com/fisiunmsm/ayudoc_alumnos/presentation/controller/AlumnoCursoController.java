package com.fisiunmsm.ayudoc_alumnos.presentation.controller;

import com.fisiunmsm.ayudoc_alumnos.application.service.AlumnoCursoService;

import com.fisiunmsm.ayudoc_alumnos.application.service.AlumnoService;
import com.fisiunmsm.ayudoc_alumnos.domain.model.AlumnoCursoDTO;
import com.fisiunmsm.ayudoc_alumnos.domain.model.inscripcionCurso.InscripcionRequest;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.AlumnoCursoTable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("v1/alucur")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class AlumnoCursoController {
    private final AlumnoCursoService alumnoCursoService;
    private final AlumnoService alumnoService;

    @GetMapping
    public Flux<AlumnoCursoTable> getAllAlumnoCursos() {
        return alumnoCursoService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<AlumnoCursoTable> getAlumnoCursoById(@PathVariable Long id) {
        return alumnoCursoService.findById(id);
    }

    @GetMapping("/estado/{estado}")
    public Flux<AlumnoCursoTable> getAlumnoCursoByEstado(@PathVariable String estado) {
        return alumnoCursoService.findByEstado(estado);
    }

    @GetMapping("/alumno/{id}")
    public Flux<AlumnoCursoDTO> getCursosByAlumnoId(@PathVariable("id") Long alumnoId) {
        return alumnoCursoService.getCursosByAlumnoId(alumnoId);
    }

    @PostMapping("/inscripcion")
    public Mono<ResponseEntity<String>> inscribirAlumno(@RequestBody InscripcionRequest request) {
        return alumnoCursoService.inscribirAlumno(request)
                .then(Mono.just(ResponseEntity.status(201).body("Inscripción exitosa"))) // 201 Created
                .onErrorResume(ex ->
                        Mono.just(ResponseEntity.status(400).body(ex.getMessage())) // 400 Bad Request con el mensaje de error personalizado
                );
    }
}
