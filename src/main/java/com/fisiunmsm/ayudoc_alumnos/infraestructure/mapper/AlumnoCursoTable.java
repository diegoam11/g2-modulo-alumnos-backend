package com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper;

import com.fisiunmsm.ayudoc_alumnos.domain.model.AlumnoCurso;
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

    public AlumnoCurso toDomainModel() {
        return new AlumnoCurso(id, alumnoid, cursoid, periodoid, institucionid, departamentoid, estado);
    }

    public static AlumnoCursoTable fromDomainModel(AlumnoCurso alumnoCurso) {
        return new AlumnoCursoTable(
                alumnoCurso.getId(),
                alumnoCurso.getAlumnoid(),
                alumnoCurso.getCursoid(),
                alumnoCurso.getPeriodoid(),
                alumnoCurso.getInstitucionid(),
                alumnoCurso.getDepartamentoid(),
                alumnoCurso.getEstado()
        );
    }
}