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
    @Query("SELECT a.*, i.nombreLargo AS universidad, pe.descripcion AS plan, d2.nombre AS facultad" + 
            " FROM usuario u" + 
            " JOIN alumno a ON u.id = a.usuarioid" +
            " JOIN institucion i on a.institucionid = i.id" + 
            " LEFT JOIN planestudios pe ON a.planid = pe.id" +
            " LEFT JOIN departamento d1 ON d1.id = pe.departamentoid" + 
            " LEFT JOIN departamento d2 ON d2.id = d1.departamentoid" + 
            " WHERE u.username = :username"
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
