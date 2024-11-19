package com.fisiunmsm.ayudoc_alumnos.security.jwt;

import com.fisiunmsm.ayudoc_alumnos.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class JwtFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().value();

        // Permitir solicitudes preflight (OPTIONS)
        if (request.getMethod() == HttpMethod.OPTIONS) {
            return chain.filter(exchange);
        }

        // Permitir solicitudes a rutas de autenticación
        if (path.contains("auth")) {
            return chain.filter(exchange);
        }

        String auth = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (auth == null) {
            return Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "No token was found."));
        }

        if (!auth.startsWith("Bearer ")) {
            return Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "Invalid token."));
        }

        String token = auth.replace("Bearer ", "");
        exchange.getAttributes().put("token", token);
        return chain.filter(exchange);
    }

}
