package com.fisiunmsm.ayudoc_alumnos.infraestructure.repository;

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
}