package com.fisiunmsm.ayudoc_alumnos.application.serviceImpl;

import com.fisiunmsm.ayudoc_alumnos.application.service.AlumnoNotaService;
import com.fisiunmsm.ayudoc_alumnos.application.service.AlumnoService;
import com.fisiunmsm.ayudoc_alumnos.domain.model.infoAca.AlumnoDetalles;
import com.fisiunmsm.ayudoc_alumnos.domain.model.infoAca.AlumnoInfo;
import com.fisiunmsm.ayudoc_alumnos.domain.model.infoAca.AlumnoInfoPartOne;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.AlumnoTable;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.repository.AlumnoRepository;
import com.fisiunmsm.ayudoc_alumnos.domain.model.Alumno;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoRepository alumnoRepository;
    private final AlumnoNotaService alumnoNotaService;

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

    @Override
    public Mono<AlumnoInfo> getInfoAcademica(Long alumnoId) {
        return Mono.zip(
                alumnoRepository.getInfoAcademicaPartOne(alumnoId),
                alumnoNotaService.obtenerNotasAprobadasConPeriodo(alumnoId).collectList()
        ).map(tuple -> {
            AlumnoInfo alumInfo = new AlumnoInfo();
            alumInfo.setAlumnoInfoPartOne(tuple.getT1());
            alumInfo.setCursosNotas(new ArrayList<>(tuple.getT2()));
            return alumInfo;
        });
    }

    @Override
    public Mono<AlumnoDetalles> getInfoAcademicaByUsername(String username) {
        return alumnoRepository.findAlumnoDetailsByUsername(username);
    }
}
