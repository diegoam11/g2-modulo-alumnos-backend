package com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper;

import com.fisiunmsm.ayudoc_alumnos.domain.model.Alumno;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import reactor.core.publisher.Mono;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table("alumno")
public class AlumnoTable {
    @Id
    private Long id;
    private String codigo;
    private String nombres;
    private String apellidos;
    private String email;
    private String estado;
    private String institucionId;
    private String departamentoId;
    @Column("usuarioid")
    private Long usuarioId;

    public Alumno toDomainModel(){
        return new Alumno(
                id, codigo, nombres, apellidos, email, estado,
                institucionId, departamentoId, usuarioId);
    }

    public Mono<Alumno> toMono(){
        return Mono.just(toDomainModel());
    }
}

