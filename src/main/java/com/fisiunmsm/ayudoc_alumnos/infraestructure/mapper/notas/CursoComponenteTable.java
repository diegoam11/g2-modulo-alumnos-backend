package com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.notas;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table("cursocomponente")
public class CursoComponenteTable {
    @Id
    private Long id;
    private String codigo;
    private String descripcion;
    private int evaluacionid;
    private Double peso;
    private String estado;
    private String cursoid;
    private int orden;
    private int padreid;
    private int nivel;
    private int institucionid;
    private String calculado;
    private int departamentoid;
    private int formulaid;
}
