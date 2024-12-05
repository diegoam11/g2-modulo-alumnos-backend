package com.fisiunmsm.ayudoc_alumnos.security.dto;

import lombok.Data;

@Data
public class VerificarCodigoDTO {
    private String email;
    private String code;
}
