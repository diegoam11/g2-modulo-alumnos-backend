package com.fisiunmsm.ayudoc_alumnos.security.service;

import com.fisiunmsm.ayudoc_alumnos.exception.CustomException;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.AlumnoTable;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.repository.AlumnoRepository;
import com.fisiunmsm.ayudoc_alumnos.security.Role;
import com.fisiunmsm.ayudoc_alumnos.security.dto.CrearUsuarioDto;
import com.fisiunmsm.ayudoc_alumnos.security.dto.LoginUsuarioDto;
import com.fisiunmsm.ayudoc_alumnos.security.dto.TokenDto;
import com.fisiunmsm.ayudoc_alumnos.security.entity.Usuario;
import com.fisiunmsm.ayudoc_alumnos.security.jwt.JwtProvider;
import com.fisiunmsm.ayudoc_alumnos.security.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AlumnoRepository alumnoRepository;

    @Transactional
    public Mono<Usuario> registrar(CrearUsuarioDto dto) {
        Usuario usuario = Usuario.builder()
                .username(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .roles(Role.ROLE_ALUMNO.name())
                .build();

        Mono<Boolean> userExists = usuarioRepository.findByUsername(usuario.getUsername()).hasElement();

        return userExists
                .flatMap(exists -> {
                    if (exists)
                       return Mono.error(new CustomException(HttpStatus.CONFLICT, "Usuario ya existe."));
                    return  usuarioRepository.save(usuario)
                            .flatMap(savedUser -> {
                                AlumnoTable alumno = AlumnoTable.builder()
                                        .codigo(dto.getCodigo())
                                        .nombres(dto.getNombres())
                                        .apellidos(dto.getApellidos())
                                        .email(dto.getEmail())
                                        .usuarioId(savedUser.getId())
                                        .build();
                                return alumnoRepository.save(alumno)
                                        .thenReturn(savedUser);
                            });
                });

    }

    public Mono<TokenDto> login(LoginUsuarioDto dto) {
        return usuarioRepository.findByUsername(dto.getUsername())
                .filter(user -> passwordEncoder.matches(dto.getPassword(), user.getPassword()))
                .flatMap(user -> jwtProvider.generateToken(user)
                        .map(TokenDto::new))
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "bad credentials")));
    }

    public  Mono<Usuario> findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    public Mono<Usuario> save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
