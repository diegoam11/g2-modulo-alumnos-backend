package com.fisiunmsm.ayudoc_alumnos.infraestructure.repository;

import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.AlumnoGrupoTable;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface AlumnoGrupoRepository extends R2dbcRepository<AlumnoGrupoTable,Void> {
}
