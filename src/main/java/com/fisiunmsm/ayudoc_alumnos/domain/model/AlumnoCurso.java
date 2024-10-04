package com.fisiunmsm.ayudoc_alumnos.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoCurso {
    @Id
    private Long id;
    private Long alumnoid;
    private Integer cursoid;
    private Integer periodoid;
    private Integer institucionid;
    private Integer departamentoid;
    private String estado;
}
