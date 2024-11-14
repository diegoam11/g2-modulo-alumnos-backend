package com.fisiunmsm.ayudoc_alumnos.presentation.controller;

import com.fisiunmsm.ayudoc_alumnos.application.service.CursoService;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.CursoTable;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("v1/cursos")
@RequiredArgsConstructor
public class CursoController {
    private final CursoService cursoService;
    @GetMapping("/cursos/{id}/codigo")
    public Mono<String> obtenerCodigoCurso(@PathVariable Long id) {
        return cursoService.generarCodigo(id);
    }
    @GetMapping("/codigo/{codigo}")
    public Mono<CursoTable> obtenerCursoPorCodigo(@PathVariable String codigo) {
        return cursoService.decodificarCodigoCurso(codigo);
    }
    @GetMapping("/{cursoId}")
    public Mono<CursoTable> obtenerCursoPorId(@PathVariable Long cursoId) {
        return cursoService.findCursoTableById(cursoId);
    }
}
