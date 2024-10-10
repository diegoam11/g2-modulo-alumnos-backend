package com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.inscripcion;
import com.fisiunmsm.ayudoc_alumnos.domain.model.inscripcion.InscripcionRequest;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.AlumnoCursoTable;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.CursoTable;
import org.springframework.stereotype.Service;

@Service
public class AlumnoCursoMapper {
    public AlumnoCursoTable toAlumnoCurso(InscripcionRequest request, CursoTable curso) {
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