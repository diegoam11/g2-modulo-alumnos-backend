package com.fisiunmsm.ayudoc_alumnos.application.serviceImpl;

import com.fisiunmsm.ayudoc_alumnos.application.service.AlumnoGrupoService;
import com.fisiunmsm.ayudoc_alumnos.domain.model.inscripcionCurso.AlumnoGrupoResponse;
import com.fisiunmsm.ayudoc_alumnos.domain.model.inscripcionCurso.GrupoResponse;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.AlumnoGrupoTable;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.repository.AlumnoCursoRepository;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.repository.AlumnoGrupoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
@AllArgsConstructor
public class AlumnoGrupoServiceImpl implements AlumnoGrupoService {
    private final AlumnoCursoRepository alumnoCursoRepository;
    private final AlumnoGrupoRepository alumnoGrupoRepository;
    @Override
    public Mono<AlumnoGrupoTable> registrarAlumnoEnGrupo(Long alumnoid, Long cursoid, Long grupoid) {
        // Buscar el alumnocursoid en la tabla alumnocurso usando alumnoId y cursoId
        return alumnoCursoRepository.findByAlumnoidAndCursoid(alumnoid, cursoid)
                .flatMap(alumnoCurso -> {
                    // Si encuentra el alumnocursoid, crear la relación en alumnogrupo
                    AlumnoGrupoTable alumnoGrupo = new AlumnoGrupoTable(alumnoCurso.getId(), grupoid);
                    return alumnoGrupoRepository.save(alumnoGrupo);
                })
                .switchIfEmpty(Mono.error(new RuntimeException("No se encontró la relación alumno-curso")));
    }

    @Override
    public Mono<GrupoResponse> findGrupobyAlumnoIdAndCursoId(Long alumnoid, Long cursoid) {
        return alumnoGrupoRepository.findGrupoByAlumnoIdAndCursoId(alumnoid,cursoid);
    }

    @Override
    public Flux<AlumnoGrupoResponse> findAlumnosByCursoIdAndGrupoId(Long cursoid, Long grupoid) {
        return alumnoGrupoRepository.findAlumnosByCursoIdAndGrupoId(cursoid,grupoid);
    }

    @Override
    public Flux<AlumnoGrupoResponse> findGruposByCursoId(Long cursoid) {
        return alumnoGrupoRepository.findAllGruposByCursoId(cursoid);
    }
}
