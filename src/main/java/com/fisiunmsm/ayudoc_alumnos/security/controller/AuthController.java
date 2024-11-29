package com.fisiunmsm.ayudoc_alumnos.security.controller;
import java.util.UUID;

import com.fisiunmsm.ayudoc_alumnos.security.dto.*;
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

    @PostMapping("reset-password/generate-code")
    public Mono<ResponseEntity<String>> forgotPassword(@RequestBody ResetPasswordDTO dto) {
        if (dto.getEmail() == null || dto.getEmail().isEmpty()) {
            return Mono.just(ResponseEntity.badRequest().body("El correo electrónico no puede estar vacío."));
        }
        return usuarioService.findByUsername(dto.getEmail())
                .flatMap(usuario -> {
                    String codigoReinicio = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
                    usuario.setCodReiniciarPassword(codigoReinicio);
                    return usuarioService.save(usuario)
                            .then(emailService.sendResetCodeEmail(dto.getEmail(), codigoReinicio))
                            .then(Mono.just(ResponseEntity.ok("Código de reinicio enviado con éxito.")));
                })
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.")));  // Si no se encuentra el usuario, devolver 404
    }

    @PostMapping("reset-password/validate-code")
    public Mono<ResponseEntity<String>> verifyResetCode(@RequestBody VerificarCodigoDTO dto) {
        if (dto.getEmail() == null || dto.getEmail().isEmpty() || dto.getCode() == null || dto.getCode().isEmpty()) {
            return Mono.just(ResponseEntity.badRequest().body("El correo electrónico y el código no pueden estar vacíos."));
        }

        return usuarioService.findByUsername(dto.getEmail())
                .flatMap(usuario -> {
                    if (dto.getCode().equals(usuario.getCodReiniciarPassword())) {
                        return Mono.just(ResponseEntity.ok("Código de reinicio verificado correctamente."));
                    } else {
                        return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("El código de reinicio es incorrecto."));
                    }
                })
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.")));
    }
}
