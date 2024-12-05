package com.fisiunmsm.ayudoc_alumnos.security.dto;

import lombok.Data;

@Data
public class CambiarPasswordDTO {
    private String email;
    private String code;
    private String newPassword;
}
