package com.fisiunmsm.ayudoc_alumnos.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Usuario {
    private Integer id;
    private String username;
    private String password;
    private String nombreVisualizar;
    private String estado;
    private String fechaCreacion;
    private String fechaValidado;
    private String fechaUltLogin;
}

