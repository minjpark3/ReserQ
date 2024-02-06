package com.sparta.reserq.config.jwt;


import com.sparta.reserq.domain.user.User;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
//사용자 인증및 권한정보를 나태내줌
public class UserDetailsImpl implements UserDetails {
    private final Long id;
    private final String email;
    private final String password;
    private final String name;

    private Collection<? extends GrantedAuthority> authorities;

    @Builder
    public UserDetailsImpl(final Long id, final String email, final String password,
                           final String name, final Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.authorities = authorities;
    }

    public static UserDetailsImpl from(User user) {


        return UserDetailsImpl.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .authorities(Set.of())
                .build();
    }
    public User toUser() {
        return User.builder()
                .id(id)
                .email(email)
                .password(password)
                .name(name)
                .build();
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
    public String getUsername() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
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