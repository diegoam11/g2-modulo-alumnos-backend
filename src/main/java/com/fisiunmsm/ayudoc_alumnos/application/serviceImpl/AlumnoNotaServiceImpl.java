package com.fisiunmsm.ayudoc_alumnos.application.serviceImpl;

import com.fisiunmsm.ayudoc_alumnos.application.service.AlumnoNotaService;
import com.fisiunmsm.ayudoc_alumnos.domain.model.infoAca.AlumnoNotasFinal;
import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.AlumnoTopReponse;
import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.NotaResponse;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.notas.NotaMapper;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.repository.AlumnoNotaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
@RequiredArgsConstructor
public class AlumnoNotaServiceImpl implements AlumnoNotaService {
    private final AlumnoNotaRepository alumnoNotaRepository;
    private final NotaMapper notaMapper;
    @Override
    public Mono<NotaResponse> getNotasDeAlumnoEnCurso(Long alumnoId, Long cursoId) {
        return alumnoNotaRepository.findNotasComponente(alumnoId, cursoId)
                .collectList()
                .map(notas -> notaMapper.toNotaResponse(cursoId, alumnoId, notas));
    }

    @Override
    public Flux<AlumnoTopReponse> getTop5NotasAlumnoPorComponente(Long cursoId,Long componenteId) {
        return alumnoNotaRepository.findTop5AlumnosByCompo(cursoId,componenteId);
    }

    @Override
    public Flux<AlumnoNotasFinal> obtenerNotasAprobadasConPeriodo(Long alumnoId) {
        return alumnoNotaRepository.findNotasFinalesConPeriodoByAlumnoId(alumnoId);
    }
}
