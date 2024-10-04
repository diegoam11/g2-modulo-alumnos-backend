package com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper;

import com.fisiunmsm.ayudoc_alumnos.domain.model.Alumno;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import reactor.core.publisher.Mono;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Table("alumno")
public class AlumnoTable {
    @Id
    private Long id;
    private String codigo;
    private String nombres;
    private String apellidos;
    private String email;
    private String estado;
    private String institucionid;
    private String departamentoid;
    private String usuarioid;

    public Alumno toDomainModel(){
        return new Alumno(
                id, codigo, nombres, apellidos, email, estado,
                institucionid, departamentoid, usuarioid);
    }

    public Mono<Alumno> toMono(){
        return Mono.just(toDomainModel());
    }
}

