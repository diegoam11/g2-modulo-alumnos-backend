package com.fisiunmsm.ayudoc_alumnos.application.serviceImpl;

import com.fisiunmsm.ayudoc_alumnos.application.service.AlumnoCursoService;
import com.fisiunmsm.ayudoc_alumnos.application.service.CursoService;
import com.fisiunmsm.ayudoc_alumnos.domain.model.AlumnoCursoDTO;
import com.fisiunmsm.ayudoc_alumnos.domain.model.inscripcionCurso.InscripcionRequest;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.AlumnoCursoTable;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.inscripcion.AlumnoCursoMapper;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.repository.AlumnoCursoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class AlumnoCursoServiceImpl implements AlumnoCursoService {
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
        return cursoService.decodificarCodigoCurso(request.getCodigo())
            .flatMap(curso -> {
                AlumnoCursoTable alumnocurso = alumnoCursoMapper.toAlumnoCurso(request, curso);
                return alumnoCursoRepository.save(alumnocurso).then();
            });
    }
}
