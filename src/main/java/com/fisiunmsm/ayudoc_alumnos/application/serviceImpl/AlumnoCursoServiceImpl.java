package com.fisiunmsm.ayudoc_alumnos.application.serviceImpl;

import com.fisiunmsm.ayudoc_alumnos.application.service.AlumnoCursoService;
import com.fisiunmsm.ayudoc_alumnos.application.service.AlumnoService;
import com.fisiunmsm.ayudoc_alumnos.application.service.CursoService;
import com.fisiunmsm.ayudoc_alumnos.domain.model.AlumnoCursoDTO;
import com.fisiunmsm.ayudoc_alumnos.domain.model.infoAca.AlumnosCursoResponse;
import com.fisiunmsm.ayudoc_alumnos.domain.model.inscripcionCurso.InscripcionRequest;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.AlumnoCursoTable;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.AlumnoTable;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.inscripcionCurso.AlumnoCursoMapper;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.repository.AlumnoCursoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class AlumnoCursoServiceImpl implements AlumnoCursoService {
    private final AlumnoService alumnoService;
    private final AlumnoCursoRepository alumnoCursoRepository;
    private final AlumnoCursoMapper alumnoCursoMapper;
    private final CursoService cursoService;
    @Override
    public Flux<AlumnoCursoTable> findAll() {
        return alumnoCursoRepository.findAll();
    }

    @Override
    public Mono<AlumnoCursoTable> findById(Long id) {
        return alumnoCursoRepository.findById(id);
    }

    @Override
    public Flux<AlumnoCursoTable> findByEstado(String estado) {
        return alumnoCursoRepository.findByEstado(estado);
    }

    @Override
    public Flux<AlumnoCursoDTO> getCursosByAlumnoId(Long alumnoId) {
        return alumnoCursoRepository.findCursosByAlumnoId(alumnoId);
    }

    @Override
    public Mono<Void> inscribirAlumno(InscripcionRequest request) {
        var alumnoMono = alumnoService.findById(request.getAlumnoid())
                .switchIfEmpty(Mono.error(new RuntimeException("Error: Alumno no encontrado"))); // Verificación de existencia del alumno

        var cursoMono = cursoService.decodificarCodigoCurso(request.getCodigo())
                .switchIfEmpty(Mono.error(new RuntimeException("Error: Código de curso no encontrado"))); // Verificación de existencia del curso

        return Mono.zip(alumnoMono, cursoMono) // Combina los resultados en paralelo
            .flatMap(alumnoCurso -> {
                var alumno = alumnoCurso.getT1(); // Alumno obtenido
                var curso = alumnoCurso.getT2(); // Curso obtenido
                AlumnoCursoTable alumnocurso = alumnoCursoMapper.toAlumnoCurso(request, curso);
                return alumnoCursoRepository.save(alumnocurso).then();
            });
    }

    @Override
    public Flux<AlumnosCursoResponse> findAlumnosByCursoId(Long cursoId) {
        return alumnoCursoRepository.findAlumnosByCursoid(cursoId);
    }
}
