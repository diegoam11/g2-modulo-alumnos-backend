package com.fisiunmsm.ayudoc_alumnos.domain.model.inscripcion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CursoDecoder {
    private Long id;
    private Long planestudiosid;
    private Long periodoacademicoid;
    private Long institucionid;
    private Long departamentoid;
}
