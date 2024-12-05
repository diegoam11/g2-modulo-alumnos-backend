package com.fisiunmsm.ayudoc_alumnos.infraestructure.repository;

import com.fisiunmsm.ayudoc_alumnos.domain.model.infoAca.AlumnoNotasFinal;
import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.competencianota.CompetenciaNotaDTO;
import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.competencianota.NotasDTO;
import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.competencianota.RankingDTO;
import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.notacomponente.AlumnoNota;
import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.top5.AlumnoTopReponse;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.notas.AlumnoNotasTable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface AlumnoNotaRepository extends R2dbcRepository<AlumnoNotasTable, Long> {

    @Query("SELECT an.id,an.alumnoid,an.cursoid,cc.id as componentenotaid, an.nota," +
            "cc.descripcion, cc.padreid, cc.calculado, cc.formulaid " +
            "FROM cursocomponente cc " +
            "LEFT JOIN alumnonotas an ON cc.id = an.componentenotaid " +
            "AND an.alumnoid = :alumnoId AND an.cursoid = :cursoId " +
            "WHERE cc.cursoid = :cursoId " +
            "ORDER BY cc.id"
    )
    Flux<AlumnoNota> findNotasComponente(@Param("alumnoId") Long alumnoId, @Param("cursoId") Long cursoId);
    @Query("SELECT an.alumnoid, an.cursoid, an.nota, an.id as notaid, " +
            "a.nombres, a.apellidos, a.codigo as codigoalumno, " +
            "cc.id as componentenotaid, cc.descripcion " +
            "FROM cursocomponente cc JOIN alumnonotas an ON cc.id = an.componentenotaid " +
            "JOIN alumno a ON an.alumnoid = a.id " +
            "WHERE cc.cursoid = :cursoId AND cc.id = :componenteId " +
            "ORDER BY an.nota DESC, an.alumnoid LIMIT 5"
    )
    Flux<AlumnoTopReponse> findTop5AlumnosByCompo(
            @Param("cursoId") Long cursoId,
            @Param("componenteId") Long componenteId
    );
    @Query("SELECT an.alumnoid, an.cursoid, " +
            " c.nombre, an.nota,c.ciclo,c.numCreditos as numcreditos , " +
            " pa.id as periodoid,pa.codigo as periodocodigo,pa.descripcion as periododescripcion" +
            " FROM alumnonotas an " +
            " INNER JOIN alumnocurso ac ON an.cursoid = ac.cursoid AND an.alumnoid = ac.alumnoid " +
            " INNER JOIN cursocomponente cc ON an.componentenotaid = cc.id " +
            " LEFT JOIN cursoDecoder c on ac.cursoid = c.id " +
            " LEFT JOIN periodoacademico pa on ac.periodoid = pa.id " +
            " WHERE an.alumnoid = :alumnoId AND cc.padreid IS NULL" +
            " ORDER BY cc.id"
    )
    Flux<AlumnoNotasFinal> findNotasFinalesConPeriodoByAlumnoId(@Param("alumnoId") Long alumnoId);

    @Query("SELECT an.alumnoid, an.cursoid, an.nota, an.id as notaid, " +
            "a.nombres, a.apellidos, a.codigo as codigoalumno, " +
            "cc.id as componentenotaid, cc.descripcion, " +
            "ROW_NUMBER() OVER (ORDER BY an.nota DESC) AS posicion " +
            "FROM cursocomponente cc " +
            "JOIN alumnonotas an ON cc.id = an.componentenotaid " +
            "JOIN alumno a ON an.alumnoid = a.id " +
            "WHERE cc.cursoid = :cursoId AND cc.id = :componenteId " +
            "ORDER BY an.nota DESC"
    )
    Flux<AlumnoTopReponse> findTopAlumnosByCompo(
            @Param("cursoId") Long cursoId,
            @Param("componenteId") Long componenteId
    );

    @Query("SELECT * FROM (" +
            "    SELECT an.alumnoid, an.cursoid, an.nota, an.id as notaid, " +
            "           a.nombres, a.apellidos, a.codigo as codigoalumno, " +
            "           cc.id as componentenotaid, cc.descripcion, " +
            "           ROW_NUMBER() OVER (ORDER BY an.nota DESC) AS posicion " +
            "    FROM cursocomponente cc " +
            "    JOIN alumnonotas an ON cc.id = an.componentenotaid " +
            "    JOIN alumno a ON an.alumnoid = a.id " +
            "    WHERE cc.cursoid = :cursoId AND cc.id = :componenteId " +
            ") AS ranked_alumnos " +
            "WHERE ranked_alumnos.alumnoid = :alumnoId")
    Flux<AlumnoTopReponse> findAlumnoPositionByCompo(
            @Param("cursoId") Long cursoId,
            @Param("componenteId") Long componenteId,
            @Param("alumnoId") Long alumnoId
    );

    @Query("SELECT " +
            "COALESCE(SUM(CASE WHEN an.nota >= 11 THEN 1 ELSE 0 END), 0) AS totalAprobados, " +
            "COALESCE(SUM(CASE WHEN an.nota < 11 THEN 1 ELSE 0 END), 0) AS totalDesaprobados " +
            "FROM alumnonotas an " +
            "WHERE an.alumnoid = :alumnoId")
    Mono<AlumnoTopReponse> countCursosAprobadosDesaprobados(
            @Param("alumnoId") Long alumnoId
    );
    @Query("SELECT " +
            "cucomp.id AS cursocompetenciaid, " +
            "cia.id AS competenciaid, cia.nombre, cia.descripcion AS competenciadescripcion, cia.tipo, " +
            "cucomp.cursoid, " +
            "cuco.codigo AS componentecodigo, cuco.descripcion AS componentedescripcion, cuco.peso AS componentepeso, " +
            "an.nota " +
            "FROM cursocompetencia cucomp " +
            "JOIN componentecompetencia comp ON cucomp.id = comp.cursocompetenciaid " +
            "JOIN cursocomponente cuco ON comp.cursocomponenteid = cuco.id " +
            "JOIN alumnonotas an ON (comp.cursocomponenteid = an.componentenotaid) AND (cucomp.cursoid = an.cursoid) " +
            "JOIN competencia cia ON cucomp.competenciaid = cia.id " +
            "WHERE cucomp.cursoid = :cursoId AND an.alumnoid = :alumnoId " +
            "ORDER BY cucomp.competenciaid")
    Flux<CompetenciaNotaDTO> findNotasCompetenciaByAlumnoAndCurso(
            @Param("cursoId") Long cursoId,
            @Param("alumnoId") Long alumnoId);
    @Query("select an.alumnoid, a.nombres, a.apellidos,a.codigo as alumnocodigo,  " +
            "cucomp.id AS cursocompetenciaid, " +
            "cia.id AS competenciaid, cucomp.cursoid, cia.codigo as competenciacodigo ,cia.nombre as nombrecompetencia, cia.descripcion AS competenciadescripcion, " +
            "cuco.peso AS componentepeso, " +
            "an.nota " +
            "from cursocompetencia cucomp " +
            "join componentecompetencia comp on cucomp.id = comp.cursocompetenciaid " +
            "join cursocomponente cuco on comp.cursocomponenteid = cuco.id " +
            "join alumnonotas an on (comp.cursocomponenteid = an.componentenotaid) AND (cucomp.cursoid = an.cursoid) " +
            "join competencia cia on cucomp.competenciaid = cia.id " +
            "join alumno a on an.alumnoid = a.id " +
            "WHERE cucomp.cursoid = :cursoId " +
            "ORDER BY an.alumnoid, cucomp.competenciaid")
    Flux<RankingDTO> findRankingCompetenciaByCursoId(@Param("cursoId") Long cursoId);

}
