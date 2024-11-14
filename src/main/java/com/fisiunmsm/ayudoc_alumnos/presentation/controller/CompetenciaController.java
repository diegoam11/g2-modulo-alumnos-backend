package com.fisiunmsm.ayudoc_alumnos.presentation.controller;

import com.fisiunmsm.ayudoc_alumnos.application.service.CompetenciaService;
import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.competencianota.CompetenciaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
@RequestMapping("v1/competencias")
public class CompetenciaController {
    private final CompetenciaService competenciaService;

    @GetMapping("/curso/{cursoId}")
    public Flux<CompetenciaDTO> obtenerCompetenciasPorCurso(@PathVariable Long cursoId) {
        return competenciaService.obtenerCompetenciasDeCurso(cursoId);
    }
}
