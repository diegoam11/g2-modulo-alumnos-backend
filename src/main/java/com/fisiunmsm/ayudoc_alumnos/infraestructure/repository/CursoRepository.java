package com.fisiunmsm.ayudoc_alumnos.infraestructure.repository;


import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.CursoTable;
import org.springframework.data.r2dbc.repository.R2dbcRepository;


public interface CursoRepository extends R2dbcRepository <CursoTable,Long>{

}
