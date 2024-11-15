package com.fisiunmsm.ayudoc_alumnos.domain.model.infoAca;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AlumnoInfo {
    private AlumnoInfoPartOne alumnoInfoPartOne;
    private ArrayList<AlumnoNotasFinal> cursosNotas;
}