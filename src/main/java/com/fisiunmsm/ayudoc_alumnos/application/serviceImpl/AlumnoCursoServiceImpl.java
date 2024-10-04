package com.fisiunmsm.ayudoc_alumnos.application.serviceImpl;

import com.fisiunmsm.ayudoc_alumnos.application.service.AlumnoCursoService;
import com.fisiunmsm.ayudoc_alumnos.domain.model.AlumnoCurso;
import com.fisiunmsm.ayudoc_alumnos.domain.model.AlumnoCursoDTO;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.repository.AlumnoCursoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AlumnoCursoServiceImpl implements AlumnoCursoService {
    private final AlumnoCursoRepository alumnoCursoRepository;

    public AlumnoCursoServiceImpl(AlumnoCursoRepository alumnoCursoRepository) {
        this.alumnoCursoRepository = alumnoCursoRepository;
    }

    @Override
    public Flux<AlumnoCurso> findAll() {
        return alumnoCursoRepository.findAll();
    }

    @Override
    public Mono<AlumnoCurso> findById(Long id) {
        return alumnoCursoRepository.findById(id);
    }

    @Override
    public Flux<AlumnoCurso> findByEstado(String estado) {
        return alumnoCursoRepository.findByEstado(estado);
    }

    @Override
    public Flux<AlumnoCursoDTO> getCursosByAlumnoId(Long alumnoId) {
        return alumnoCursoRepository.findCursosByAlumnoId(alumnoId);
    }

}
