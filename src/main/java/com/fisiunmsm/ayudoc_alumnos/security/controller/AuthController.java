package com.fisiunmsm.ayudoc_alumnos.security.controller;

import com.fisiunmsm.ayudoc_alumnos.security.dto.CrearUsuarioDto;
import com.fisiunmsm.ayudoc_alumnos.security.dto.LoginUsuarioDto;
import com.fisiunmsm.ayudoc_alumnos.security.dto.TokenDto;
import com.fisiunmsm.ayudoc_alumnos.security.entity.Usuario;
import com.fisiunmsm.ayudoc_alumnos.security.service.UsuarioService;
import com.fisiunmsm.ayudoc_alumnos.validation.CustomValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RequestMapping("/v1/auth")
@RestController
public class AuthController {
    private final UsuarioService usuarioService;
    private final CustomValidator customValidator;

    @PostMapping("registrar")
    public Mono<Usuario> registrar(@RequestBody CrearUsuarioDto dto) {
        customValidator.validate(dto);
        return usuarioService.registrar(dto);
    }

    @PostMapping("login")
    public Mono<TokenDto> login(@RequestBody LoginUsuarioDto dto){
        customValidator.validate(dto);
        return usuarioService.login(dto);
    }
}
