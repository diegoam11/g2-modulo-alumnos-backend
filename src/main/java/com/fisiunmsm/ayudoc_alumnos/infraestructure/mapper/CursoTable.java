package com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table("curso")
public class CursoTable {

    @Id
    private Long id;
    private String codigo;
    private String nombre;
    private String tipo;
    private Long numhorasteoria;
    private Long numhoraspractica;
    private Long numhoraslaboratorio;
    private Long numcreditos;
    private Long planestudiosid;
    private String ciclo;
    private Long periodoacademicoid;
    private Long institucionid;
    private Long departamentoid;
    private String estado;
    private String sumilla;
    private String modalidad;
    private String etiquetas;

}
