package com.fisiunmsm.ayudoc_alumnos.security.repository;

import com.fisiunmsm.ayudoc_alumnos.security.entity.Usuario;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface UsuarioRepository extends R2dbcRepository<Usuario, Long> {
    Mono<Usuario> findByUsername(String username);
}
