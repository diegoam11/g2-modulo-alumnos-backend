package com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table("alumnogrupo")
public class AlumnoGrupoTable {
    private Long alumnocursoid;
    private Long grupoid;
}
