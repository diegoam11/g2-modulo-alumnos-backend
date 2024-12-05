package com.fisiunmsm.ayudoc_alumnos.domain.model.infoAca;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AlumnoInfoPartOne {
    private int estado;
    private String universidad;
    private String facultad;
}
