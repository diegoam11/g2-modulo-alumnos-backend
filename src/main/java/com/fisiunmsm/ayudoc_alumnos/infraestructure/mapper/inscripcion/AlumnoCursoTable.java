package com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.inscripcion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table("alumnocurso")
public class AlumnoCursoTable {
    @Id
    private Long id;
    private Long alumnoid;
    private Long cursoid;
    private Long periodoid;
    private Long institucionid;
    private Long departamentoid;
    private String estado;
}
