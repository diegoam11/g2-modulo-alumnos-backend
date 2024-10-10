package com.fisiunmsm.ayudoc_alumnos.presentation.controller;

import com.fisiunmsm.ayudoc_alumnos.application.service.AlumnoCursoService;

import com.fisiunmsm.ayudoc_alumnos.domain.model.AlumnoCursoDTO;
import com.fisiunmsm.ayudoc_alumnos.domain.model.inscripcion.InscripcionRequest;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.AlumnoCursoTable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api-alucur/v1")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class AlumnoCursoController {
    private final AlumnoCursoService alumnoCursoService;

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
                .then(Mono.just(ResponseEntity.ok("Inscripción exitosa")))
                .onErrorReturn(ResponseEntity.badRequest().body("Error en la inscripción"));
    }

}
