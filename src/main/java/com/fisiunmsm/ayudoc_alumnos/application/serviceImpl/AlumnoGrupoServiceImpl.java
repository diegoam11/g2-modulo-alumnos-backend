package com.fisiunmsm.ayudoc_alumnos.application.serviceImpl;

import com.fisiunmsm.ayudoc_alumnos.application.service.AlumnoGrupoService;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.inscripcion.AlumnoGrupoTable;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.repository.AlumnoCursoRepository;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.repository.AlumnoGrupoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
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
}
