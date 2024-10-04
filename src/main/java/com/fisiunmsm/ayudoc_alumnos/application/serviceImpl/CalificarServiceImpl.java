package com.fisiunmsm.ayudoc_alumnos.application.serviceImpl;

import com.fisiunmsm.ayudoc_alumnos.application.service.CalificarService;
import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.NotaResponse;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.notas.AlumnoNotasTable;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.notas.NotaMapper;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.repository.AlumnoNotaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CalificarServiceImpl implements CalificarService {
    private final NotaMapper notaMapper;

    private final AlumnoNotaRepository alumnonotasRepository;

    public Mono<NotaResponse> crearNota(Long alumnoId, Long cursoId, Long componentenotaId, Double nota) {
        AlumnoNotasTable nuevaNota = new AlumnoNotasTable();
        nuevaNota.setAlumnoid(alumnoId);
        nuevaNota.setCursoid(cursoId);
        nuevaNota.setComponentenotaid(componentenotaId);
        nuevaNota.setNota(nota);

        return alumnonotasRepository.save(nuevaNota)
                .then(alumnonotasRepository.findNotasComponente(alumnoId, cursoId)
                        .collectList()
                        .map(notas -> notaMapper.toNotaResponse(cursoId, alumnoId, notas))
                );
    }

    public Mono<NotaResponse> calificarNota(Long alumnoId, Long cursoId, Long componentenotaId, Double nuevaNota) {
        return alumnonotasRepository.updateNota(nuevaNota, componentenotaId, alumnoId, cursoId)
                .then(alumnonotasRepository.findNotasComponente(alumnoId, cursoId)
                        .collectList()
                        .map(notas -> notaMapper.toNotaResponse(cursoId, alumnoId, notas))
                )
                .onErrorResume(ex -> crearNota(alumnoId, cursoId, componentenotaId, nuevaNota));
    }
}
