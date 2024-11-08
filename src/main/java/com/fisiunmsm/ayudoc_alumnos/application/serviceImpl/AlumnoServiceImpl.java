package com.fisiunmsm.ayudoc_alumnos.application.serviceImpl;

import com.fisiunmsm.ayudoc_alumnos.application.service.AlumnoService;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.AlumnoTable;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.repository.AlumnoRepository;
import com.fisiunmsm.ayudoc_alumnos.domain.model.Alumno;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoRepository alumnoRepository;

    @Override
    public Flux<Alumno> findAll() {
        return alumnoRepository.findAll().flatMap(AlumnoTable::toMono);
    }

    @Override
    public Mono<Alumno> findById(Long id) {
        return alumnoRepository.findById(id).flatMap(AlumnoTable::toMono);
    }
    @Override
    public Mono<Alumno> findAlumnoByUsername(String username) {
        return alumnoRepository.findAlumnobyUsername(username).flatMap(AlumnoTable::toMono);
    }

    @Override
    public Mono<Long> findIdByUsername(String username) {
        return alumnoRepository.findIdbyUsername(username);
    }
}
