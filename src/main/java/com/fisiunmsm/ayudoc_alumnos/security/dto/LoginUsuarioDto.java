package com.fisiunmsm.ayudoc_alumnos.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginUsuarioDto {
    @Email(message = "{validate.email.message}")
    @NotBlank(message = "{validate.notblank.message}")
    private String username;
    @NotBlank(message = "{validate.notblank.message}")
    private String password;
}
