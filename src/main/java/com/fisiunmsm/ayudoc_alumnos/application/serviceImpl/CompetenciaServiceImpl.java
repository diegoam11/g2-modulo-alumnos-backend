package com.fisiunmsm.ayudoc_alumnos.application.serviceImpl;

import com.fisiunmsm.ayudoc_alumnos.application.service.CompetenciaService;
import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.competencianota.CompetenciaDTO;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.notas.CompetenciaMapper;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.repository.CompetenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class CompetenciaServiceImpl implements CompetenciaService {
    private final CompetenciaRepository competenciaRepository;
    private final CompetenciaMapper competenciaMapper;

    @Override
    public Flux<CompetenciaDTO> obtenerCompetenciasDeCurso(Long cursoId) {
        return competenciaRepository.findCompetenciasByCursoId(cursoId)
                .map(competenciaMapper::toCompetenciaDTO);
    }
}
