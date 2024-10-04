package com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.inscripcion.mapper;

import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.AlumnoCursoTable;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.CursoTable;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.inscripcion.DTO.AlumnoCursoRequest;
import org.springframework.stereotype.Service;

@Service
public class AlumnoCursoMapper {
    public AlumnoCursoTable toAlumnoCurso(AlumnoCursoRequest request, CursoTable curso) {
                return AlumnoCursoTable.builder()
                .alumnoid(request.getAlumnoid())
                .cursoid(curso.getId())
                .periodoid(curso.getPeriodoacademicoid())
                .institucionid(curso.getInstitucionid())
                .departamentoid(curso.getDepartamentoid())
                .estado("1")
                .build();
    }
}
