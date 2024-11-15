package com.fisiunmsm.ayudoc_alumnos.infraestructure.repository;

import com.fisiunmsm.ayudoc_alumnos.domain.model.infoAca.AlumnoNotasFinal;
import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.AlumnoNota;
import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.AlumnoTopReponse;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.notas.AlumnoNotasTable;
import org.springframework.data.r2dbc.repository.Modifying;
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
    //testing purposes
    @Modifying
    @Query("UPDATE alumnonotas SET nota = :nota " +
            "WHERE componentenotaid = :componentenotaid " +
            "AND alumnoid = :alumnoId AND cursoid = :cursoId")
    Mono<Void> updateNota(@Param("nota") Double nota,@Param("componentenotaid") Long componentenotaid,
                          @Param("alumnoId") Long alumnoId,@Param("cursoId") Long cursoId);
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
}
