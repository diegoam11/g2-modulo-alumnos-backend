package com.fisiunmsm.ayudoc_alumnos.presentation.controller;

import com.fisiunmsm.ayudoc_alumnos.application.service.AlumnoNotaService;
import com.fisiunmsm.ayudoc_alumnos.application.service.CompetenciaService;
import com.fisiunmsm.ayudoc_alumnos.domain.model.infoAca.AlumnoNotasFinal;
import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.competencianota.CompetenciaNotaDTO;
import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.competencianota.RankingResultadoDTO;
import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.notacomponente.AlumnoNota;
import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.top5.AlumnoTopReponse;
import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.notacomponente.NotaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
@RequestMapping("v1/notas")
public class AlumnoNotasController {
    private final AlumnoNotaService notasService;
    private final CompetenciaService competenciaService;

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
    @GetMapping("/finales/alumno/{alumnoId}")
    public Flux<AlumnoNotasFinal> obtenerNotasAprobadasConPeriodo(@PathVariable Long alumnoId) {
        return notasService.obtenerNotasAprobadasConPeriodo(alumnoId);
    }
    @GetMapping("/competencias/curso/{cursoId}/alumno/{alumnoId}")
    public Flux<CompetenciaNotaDTO> findNotasCompetenciaByAlumnoAndCurso(
            @PathVariable Long cursoId,
            @PathVariable Long alumnoId) {
        return notasService.findNotasCompetenciaByAlumnoAndCurso(cursoId, alumnoId);
    }
    @GetMapping("/ranking/{cursoId}")
    public Mono<List<RankingResultadoDTO>> rankingCompetencia(@PathVariable Long cursoId) {
        return competenciaService.rankingCompetencia(cursoId); // Ya devuelve un Mono<List<RankingResultadoDTO>>
    }
    @GetMapping("/evaluaciones/{alumnoId}")
    public Flux<AlumnoNota> findNotasEvaluaciones(@PathVariable Long alumnoId) {
        return notasService.findNotasEvaluaciones(alumnoId);
    }
}
