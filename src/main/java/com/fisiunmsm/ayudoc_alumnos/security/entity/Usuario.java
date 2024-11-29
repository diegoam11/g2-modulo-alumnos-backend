package com.fisiunmsm.ayudoc_alumnos.security.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("usuario")
public class Usuario implements UserDetails {
    @Id
    @Getter
    private Long id;
    private String username;
    private String password;
    @Column("nombrevisualizar")
    private String nombreVisualizar;
    private String estado;
    @Column("fechacreacion")
    private LocalDate fechaCreacion;
    @Column("fechavalidado")
    private LocalDate fechaValidado;
    @Column("fechaultlogin")
    private LocalDate fechaUltLogin;
    private String roles;
    @Setter
    @Getter
    @Column("codreiniciarpassword")
    private String codReiniciarPassword;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Stream.of(roles.split(", "))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

