package com.fisiunmsm.ayudoc_alumnos.security.controller;
import java.util.UUID;

import com.fisiunmsm.ayudoc_alumnos.exception.CustomException;
import com.fisiunmsm.ayudoc_alumnos.security.dto.CrearUsuarioDto;
import com.fisiunmsm.ayudoc_alumnos.security.dto.LoginUsuarioDto;
import com.fisiunmsm.ayudoc_alumnos.security.dto.ResetPasswordDTO;
import com.fisiunmsm.ayudoc_alumnos.security.dto.TokenDto;
import com.fisiunmsm.ayudoc_alumnos.security.entity.Usuario;
import com.fisiunmsm.ayudoc_alumnos.security.service.EmailService;
import com.fisiunmsm.ayudoc_alumnos.security.service.UsuarioService;
import com.fisiunmsm.ayudoc_alumnos.validation.CustomValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RequestMapping("/v1/auth")
@RestController
public class AuthController {
    private final UsuarioService usuarioService;
    private final CustomValidator customValidator;
    private final EmailService emailService;

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

    @PostMapping("forgot-password")
    public Mono<ResponseEntity<String>> forgotPassword(@RequestBody ResetPasswordDTO dto) {
        if (dto.getEmail() == null || dto.getEmail().isEmpty()) {
            return Mono.just(ResponseEntity.badRequest().body("El correo electrónico no puede estar vacío."));
        }
        return usuarioService.findByUsername(dto.getEmail())  // Buscar el usuario por email
                .flatMap(usuario -> {
                    String codigoReinicio = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
                    usuario.setCodReiniciarPassword(codigoReinicio);
                    return usuarioService.save(usuario)
                            .then(emailService.sendResetCodeEmail(dto.getEmail(), codigoReinicio)) // Enviar código por correo
                            .then(Mono.just(ResponseEntity.ok("Código de reinicio enviado con éxito.")));
                })
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.")));  // Si no se encuentra el usuario, devolver 404
    }


    @PostMapping("get-user")
    public Mono<ResponseEntity<Usuario>> getUser(@RequestBody ResetPasswordDTO dto) {
        return usuarioService.findByUsername(dto.getEmail())
                .map(usuario -> ResponseEntity.ok(usuario))  // Si el usuario existe, devolvemos un 200 con el usuario
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));  // Si no se encuentra, devolvemos 404
    }

}
