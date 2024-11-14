package com.fisiunmsm.ayudoc_alumnos.infraestructure.repository;

import com.fisiunmsm.ayudoc_alumnos.domain.model.infoAca.AlumnoNotasFinal;
import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.competencianota.NotasDTO;
import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.notacomponente.AlumnoNota;
import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.top5.AlumnoTopReponse;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.notas.AlumnoNotasTable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

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

    @Query("SELECT an.id as notaid, an.nota, " +
            "cuco.id AS componentenotaid, cuco.cursoid as curso_id, " +
            "cuco.codigo as componentecodigo, cuco.descripcion as componentedescripcion " +
            "FROM cursocomponente cuco " +
            "INNER JOIN alumnonotas an ON cuco.id = an.componentenotaid " +
            "AND an.alumnoid = :alumnoId AND an.cursoid = :cursoId " +
            "LEFT JOIN cursocompetencia cuc ON cuco.cursoid = cuc.cursoid " +
            "LEFT JOIN competencia cia ON cuc.competenciaid = cia.id " +
            "WHERE cia.id = :competenciaId " +
            "GROUP BY an.alumnoid, an.nota, cuco.id, cuco.cursoid, cia.id, " +
            "cia.codigo, cia.nombre, cuco.codigo, an.id " +
            "ORDER BY cia.id, cuco.id")
    Flux<NotasDTO> findNotasByCompetenciaCursoAlumno(
            @Param("competenciaId") Long competenciaId,
            @Param("cursoId") Long cursoId,
            @Param("alumnoId") Long alumnoId);
}
