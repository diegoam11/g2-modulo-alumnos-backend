package com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.notas;

import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.competencianota.CompetenciaDTO;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.CompetenciaTable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetenciaMapper {
    public CompetenciaDTO toCompetenciaDTO(CompetenciaTable competenciaTable){
        return new CompetenciaDTO(
                competenciaTable.getId(),
                competenciaTable.getCodigo(),
                competenciaTable.getNombre(),
                competenciaTable.getDescripcion(),
                competenciaTable.getTipo()

        );
    }
}
