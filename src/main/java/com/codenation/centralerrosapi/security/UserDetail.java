package com.codenation.centralerrosapi.security;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.codenation.centralerrosapi.model.enums.PerfilUsuarioEnum;

public class UserDetail implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String email;
    private String senha;
    private Collection<? extends GrantedAuthority> authorities;// Perfis

    public UserDetail() {
    }

    public UserDetail(Long id, String email, String senha) {
        super();
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.authorities = Collections.emptyList();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Implementa aqui a regra de negocio para conta Expirada
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Implementa aqui a regra de negocio para conta Bloqueada
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Implementa aqui a regra de negocio para credencias expirada
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Implementa aqui a regra de negocio para verificar se usuario
        // ativo
        return true;
    }

    public boolean hasRole(PerfilUsuarioEnum perfil) {
        return getAuthorities().contains(new SimpleGrantedAuthority(perfil.getDescricao()));
    }
}




