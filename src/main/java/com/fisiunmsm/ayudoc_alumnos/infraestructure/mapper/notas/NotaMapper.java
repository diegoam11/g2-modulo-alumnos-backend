package com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.notas;


import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.notacomponente.AlumnoNota;
import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.notacomponente.ComponenteNota;
import com.fisiunmsm.ayudoc_alumnos.domain.model.notas.notacomponente.NotaResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotaMapper {
    public NotaResponse toNotaResponse(Long cursoId, Long alumnoId, List<AlumnoNota> notas) {
        List<ComponenteNota> componentesNotas = notas.stream()
                .map(nota -> new ComponenteNota(
                        nota.getComponentenotaid(),
                        nota.getNota(),
                        nota.getDescripcion(),
                        nota.getPadreid(),
                        nota.getCalculado(),
                        nota.getFormulaid()
                ))
                .toList();

        return new NotaResponse(cursoId, alumnoId, componentesNotas);
    }
}
