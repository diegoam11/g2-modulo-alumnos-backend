package com.fisiunmsm.ayudoc_alumnos.application.serviceImpl;

import com.fisiunmsm.ayudoc_alumnos.application.service.AlumnoCursoService;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.inscripcion.AlumnoCursoTable;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.inscripcion.DTO.AlumnoCursoRequest;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.inscripcion.mapper.AlumnoCursoMapper;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.repository.AlumnoCursoRepository;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.repository.CursoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
@Service
@AllArgsConstructor
public class AlumnoCursoServiceImpl implements AlumnoCursoService {
    private final AlumnoCursoRepository alumnoCursoRepository;
    private final CursoRepository cursoRepository;
    private final AlumnoCursoMapper alumnoCursoMapper;
    @Override
    public Mono<AlumnoCursoTable> agregarAlumnoACurso(AlumnoCursoRequest request) {
        return cursoRepository.findById(request.getCursoid())
            .flatMap(curso -> {
                AlumnoCursoTable alumnocurso = alumnoCursoMapper.toAlumnoCurso(request, curso);
                return alumnoCursoRepository.save(alumnocurso);
            });
    }
}
