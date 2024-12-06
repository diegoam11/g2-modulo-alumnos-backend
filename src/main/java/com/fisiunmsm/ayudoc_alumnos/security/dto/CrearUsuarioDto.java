package com.fisiunmsm.ayudoc_alumnos.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CrearUsuarioDto {
    @NotBlank(message = "{validate.notblank.message}")
    private String codigo;
    @NotBlank(message = "{validate.notblank.message}")
    private String nombres;
    @NotBlank(message = "{validate.notblank.message}")
    private String apellidos;
    @NotBlank(message = "{validate.notblank.message}")
    @Email(message = "{validate.email.message}")
    private String email;
    @NotBlank(message = "{validate.notblank.message}")
    private String password;
    @NotNull(message = "{validate.notnull.message}")
    private Long institucionid;
    @NotNull(message = "{validate.notnull.message}")
    private Long departamentoid;
}
