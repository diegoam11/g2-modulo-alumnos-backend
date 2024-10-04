package com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.notas;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table("alumnonotas")
public class AlumnoNotasTable {
    @Id
    private Long id;
    private Long alumnoid;
    private Long cursoid;
    private Long componentenotaid;
    private Double nota;
}
