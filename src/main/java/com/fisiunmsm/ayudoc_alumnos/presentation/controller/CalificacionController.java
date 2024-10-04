package com.fisiunmsm.ayudoc_alumnos.presentation.controller;

import com.fisiunmsm.ayudoc_alumnos.application.service.CalificarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api-calificar/v1")
@RequiredArgsConstructor
public class CalificacionController {
    private final CalificarService calificarService;

    @PostMapping("/a/{alumnoId}/c/{cursoId}/cc/{componenteId}")
    public Mono<ResponseEntity<String>> crearNota(
            @PathVariable Long alumnoId,
            @PathVariable Long cursoId,
            @PathVariable Long componenteId,
            @RequestBody Map<String, Double> body) {

        Double nuevaNota = body.get("nuevaNota");
        return calificarService.crearNota(alumnoId, cursoId, componenteId, nuevaNota)
                .then(Mono.just(ResponseEntity.ok("Nota calificada exitosamente")));
    }

    @PutMapping("/a/{alumnoId}/c/{cursoId}/cc/{componenteId}")
    public Mono<ResponseEntity<String>> calificarNota(
            @PathVariable Long alumnoId,
            @PathVariable Long cursoId,
            @PathVariable Long componenteId,
            @RequestBody Map<String, Double> body) {

        Double nuevaNota = body.get("nuevaNota");
        return calificarService.calificarNota(alumnoId, cursoId, componenteId, nuevaNota)
                .then(Mono.just(ResponseEntity.ok("Nota actualizada exitosamente")));
    }
}
