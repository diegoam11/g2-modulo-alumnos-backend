package com.fisiunmsm.ayudoc_alumnos.security.jwt;

import com.fisiunmsm.ayudoc_alumnos.infraestructure.mapper.AlumnoTable;
import com.fisiunmsm.ayudoc_alumnos.infraestructure.repository.AlumnoRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.expiration}")
    private long EXPIRATION;

    private final AlumnoRepository alumnoRepository;

    public Mono<String> generateToken(UserDetails userDetails) {
        return alumnoRepository.findAlumnobyUsername(userDetails.getUsername())
                .flatMap(alumno -> {
                    String token = Jwts.builder()
                            .setSubject(userDetails.getUsername())
                            .claim("roles", userDetails.getAuthorities())
                            .claim("userId", alumno.getId()) // Añadir el userId al JWT
                            .setIssuedAt(new Date())
                            .setExpiration(new Date(new Date().getTime() + EXPIRATION * 1000))
                            .signWith(getSignInKey())
                            .compact();
                    return Mono.just(token);
                });
    }


    private Key getSignInKey(){
        byte[] keyBytes  = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private <T> T extractClaim(String token, Function<Claims, T> resolver){
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean isValid(String token, UserDetails user){
        String usernameToken = extractUsername(token);
        return usernameToken.equals(user.getUsername()) && !isTokenExpired(token);
    }

    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
            return true;
        } catch (ExpiredJwtException e) {
            log.error("Token expired.");
        } catch (UnsupportedJwtException e) {
            log.error("Token unsupported.");
        } catch (MalformedJwtException e) {
            log.error("Token malformed.");
        } catch (SignatureException e) {
            log.error("Token signature error.");
        } catch (IllegalArgumentException e) {
            log.error("Token invalid.");
        }
        return false;
    }
}
