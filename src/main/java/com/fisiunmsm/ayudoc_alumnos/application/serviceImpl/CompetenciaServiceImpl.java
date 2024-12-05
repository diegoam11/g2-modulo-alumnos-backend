package com.fisiunmsm.ayudoc_alumnos.application.serviceImpl;

import com.fisiunmsm.ayudoc_alumnos.application.service.CompetenciaService;
import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.competencianota.CompetenciaDTO;
import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.competencianota.RankingDTO;
import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.competencianota.RankingHelper;
import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.competencianota.RankingResultadoDTO;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.notas.CompetenciaMapper;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.repository.AlumnoNotaRepository;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.repository.CompetenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompetenciaServiceImpl implements CompetenciaService {
    private final CompetenciaRepository competenciaRepository;
    private final CompetenciaMapper competenciaMapper;
    private final AlumnoNotaRepository alumnoNotaRepository;
    private final RankingHelper rankingHelper;

    @Override
    public Flux<CompetenciaDTO> obtenerCompetenciasDeCurso(Long cursoId) {
        return competenciaRepository.findCompetenciasByCursoId(cursoId)
                .map(competenciaMapper::toCompetenciaDTO);
    }


    @Override
    public Mono<List<RankingResultadoDTO>> rankingCompetencia(Long cursoId) {
        return alumnoNotaRepository.findRankingCompetenciaByCursoId(cursoId)
                .groupBy(ranking -> ranking.getAlumnoid() + "-" + ranking.getCompetenciaid()) // Agrupar por alumnoId y competenciaId
                .flatMap(grupo -> grupo.collectList() // Recoger las notas de cada grupo
                        .flatMap(notas -> {
                            // Calcular notas ponderadas
                            RankingResultadoDTO resultado = rankingHelper.calcularNotaPonderada(notas);
                            return Mono.just(resultado); // Devolver el resultado en Mono
                        }))
                .collectList() // Recolectar todos los resultados en una lista
                .map(rankingHelper::ordenarRanking); // Ordenar la lista antes de devolverla
    }



}
