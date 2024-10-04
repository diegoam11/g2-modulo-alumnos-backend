package com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.inscripcion.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoCursoRequest {
    private Long alumnoid;
    private Long cursoid;
}
