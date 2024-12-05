package com.fisiunmsm.ayudoc_alumnos.presentation.controller;

import com.fisiunmsm.ayudoc_alumnos.application.service.AlumnoGrupoService;
import com.fisiunmsm.ayudoc_alumnos.domain.model.inscripcionCurso.AlumnoGrupoResponse;
import com.fisiunmsm.ayudoc_alumnos.domain.model.inscripcionCurso.GrupoResponse;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.AlumnoGrupoTable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("v1/grupos")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class AlumnoGrupoController {
    private final AlumnoGrupoService grupoService;

    @PostMapping("/asignar")
    public Mono<ResponseEntity<AlumnoGrupoTable>> asignarAlumnoAGrupo(
            @RequestBody Map<String, Object> request) {
        Long alumnoid = ((Number) request.get("alumnoid")).longValue();
        Long cursoid = ((Number) request.get("cursoid")).longValue();
        Long grupoid = ((Number) request.get("grupoid")).longValue();

        return grupoService.registrarAlumnoEnGrupo(alumnoid, cursoid, grupoid)
                .map(ResponseEntity::ok)
                .onErrorResume(ex -> Mono.just(ResponseEntity.badRequest().build()));
    }
    @GetMapping("/curso/{cursoId}/alumno/{alumnoId}")
    public Mono<GrupoResponse> findGrupoByAlumnoIdAndCursoId(
            @PathVariable Long alumnoId,@PathVariable Long cursoId) {
        return grupoService.findGrupobyAlumnoIdAndCursoId(alumnoId, cursoId);
    }
    @GetMapping("/curso/{cursoId}/grupo/{grupoId}")
    public Flux<AlumnoGrupoResponse> findAlumnosByCursoIdAndGrupoId(
            @PathVariable Long cursoId,@PathVariable Long grupoId
    ){
        return grupoService.findAlumnosByCursoIdAndGrupoId(cursoId, grupoId);
    }
    @GetMapping("/curso/{cursoId}")
    public Flux<AlumnoGrupoResponse> findAllGruposByCursoId(
            @PathVariable Long cursoId
    ){
        return grupoService.findGruposByCursoId(cursoId);
    }
}
