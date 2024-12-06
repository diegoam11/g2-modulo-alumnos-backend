package com.fisiunmsm.ayudoc_alumnos.infraestructure.repository;

import com.fisiunmsm.ayudoc_alumnos.domain.model.AlumnoInfoDTO;
import com.fisiunmsm.ayudoc_alumnos.domain.model.infoAca.AlumnoInfoPartOne;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.AlumnoTable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface AlumnoRepository extends R2dbcRepository<AlumnoTable, Long> {
    @Query("SELECT a.*, i.nombreLargo AS universidad, de.nombre as facultad, pe.descripcion as plan " +
            "FROM usuario u " +
            "JOIN alumno a ON u.id = a.usuarioid " +
            "JOIN institucion i ON a.institucionid = i.id " +
            "JOIN departamento de on a.departamentoid = de.id " +
            "LEFT JOIN planestudios pe on i.id = pe.id " +
            "WHERE u.username = :username"
    )
    Mono<AlumnoInfoDTO> findAlumnobyUsername(@Param("username") String username);
    @Query("SELECT a.id " +
            " FROM usuario u" +
            " JOIN alumno a" +
            " ON u.id = a.usuarioid WHERE username = :username"
    )
    Mono<Long> findIdbyUsername(@Param("username") String username);

    @Query("SELECT al.estado AS estado, i.nombreLargo AS universidad, d.nombre AS facultad " +
            "FROM alumno al " +
            "INNER JOIN institucion i ON i.id = al.institucionid " +
            "INNER JOIN departamento d ON d.id = al.departamentoid " +
            "WHERE al.id = :alumnoId")
    Mono<AlumnoInfoPartOne> getInfoAcademicaPartOne(@Param("alumnoId") Long alumnoId);
}
