package com.fisiunmsm.ayudoc_alumnos.infraestructure.repository;

import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.CompetenciaTable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
@Repository
public interface CompetenciaRepository extends R2dbcRepository <CompetenciaTable, Long> {
    @Query("SELECT cia.* FROM competencia cia " +
            "JOIN cursocomponente cuco ON cuco.cursoid = :cursoId " +
            "JOIN cursocompetencia cuc ON cuco.cursoid = cuc.cursoid " +
            "WHERE cuc.competenciaid = cia.id " +
            "GROUP BY cia.id")
    Flux<CompetenciaTable> findCompetenciasByCursoId(Long cursoId);
}
