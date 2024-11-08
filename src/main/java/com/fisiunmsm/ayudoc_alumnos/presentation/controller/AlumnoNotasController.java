package com.fisiunmsm.ayudoc_alumnos.presentation.controller;

import com.fisiunmsm.ayudoc_alumnos.application.service.AlumnoNotaService;
import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.AlumnoTopReponse;
import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.NotaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
@RequestMapping("v1/notas")
public class AlumnoNotasController {
    private final AlumnoNotaService notasService;

    @GetMapping("/curso/{cursoId}/alumno/{alumnoId}")
    public Mono<NotaResponse> getNotasDeAlumnoEnCurso(
            @PathVariable Long cursoId,
            @PathVariable Long alumnoId) {
        return notasService.getNotasDeAlumnoEnCurso(alumnoId, cursoId);
    }
    @GetMapping("/top5/c/{cursoId}/cc/{componenteId}")
    public Flux<AlumnoTopReponse> getTop5Notas(
            @PathVariable("cursoId") Long cursoId,
            @PathVariable("componenteId") Long componenteId
            ) {
        return notasService.getTop5NotasAlumnoPorComponente(cursoId, componenteId);
    }
}
