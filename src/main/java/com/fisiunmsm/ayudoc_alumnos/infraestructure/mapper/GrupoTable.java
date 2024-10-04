package com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper;

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
@Table("grupo")
public class GrupoTable {
    @Id
    private Long id;
    private String codigo;
    private String estado;
    private Long institucionid;
}
