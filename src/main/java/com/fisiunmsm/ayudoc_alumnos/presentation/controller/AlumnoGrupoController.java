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
    public Mono<ResponseEntity<String>> asignarAlumnoAGrupo(
            @RequestBody Map<String, Object> request) {
        Long alumnoid = ((Number) request.get("alumnoid")).longValue();
        Long cursoid = ((Number) request.get("cursoid")).longValue();
        Long grupoid = ((Number) request.get("grupoid")).longValue();
         //validacion
        return grupoService.findGrupobyAlumnoIdAndCursoId(alumnoid, cursoid)
                .flatMap(existingGroup ->
                        Mono.just(ResponseEntity.badRequest()
                                .body("El alumno ya está inscrito en otro grupo del curso.")))
                .switchIfEmpty(
                        grupoService.registrarAlumnoEnGrupo(alumnoid, cursoid, grupoid)
                                .map(savedGroup -> ResponseEntity.ok("Alumno inscrito correctamente"))
                                .onErrorResume(ex -> Mono.just(ResponseEntity.badRequest()
                                        .body("Ocurrió un error al inscribir al alumno.")))
                );
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
