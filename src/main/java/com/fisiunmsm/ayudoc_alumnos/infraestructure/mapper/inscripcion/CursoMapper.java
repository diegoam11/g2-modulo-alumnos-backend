package com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.inscripcion;

import com.fisiunmsm.ayudoc_alumnos.domain.model.inscripcion.CursoDecoder;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.CursoTable;
import org.springframework.stereotype.Service;

@Service
public class CursoMapper {
    public CursoTable toEntity(CursoDecoder cursoDecoder) {
        if (cursoDecoder == null) {
            return null;
        }

        CursoTable cursoTable = new CursoTable();
        cursoTable.setId(cursoDecoder.getId());
        cursoTable.setPlanestudiosid(cursoDecoder.getPlanestudiosid());
        cursoTable.setPeriodoacademicoid(cursoDecoder.getPeriodoacademicoid());
        cursoTable.setInstitucionid(cursoDecoder.getInstitucionid());
        cursoTable.setDepartamentoid(cursoDecoder.getDepartamentoid());
        // Mapear otros atributos según sea necesario

        return cursoTable;
    }
}
