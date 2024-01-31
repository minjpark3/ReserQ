package com.sparta.reserq.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    public SecurityConfig(final JwtAuthenticationFilter jwtAuthenticationFilter, final CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
    }

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/","/api/**").permitAll()
                        .requestMatchers("api/join").permitAll()
                        .requestMatchers("/api/verify").permitAll()
                        .requestMatchers( "/api/login").permitAll()
                        .requestMatchers( "api/posts/**").permitAll()
                        .requestMatchers("/api/comment/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/refreshToken").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(handle -> handle.authenticationEntryPoint(customAuthenticationEntryPoint))
                .build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

//    @Bean
//    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
//        return http
//                .csrf(csrf -> csrf.disable())
//                .sessionManagement(sessionManagement -> sessionManagement
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(HttpMethod.POST,"api/join").permitAll()
//                        .requestMatchers(HttpMethod.POST,"/api/verify").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/api/login").permitAll()
//                        .requestMatchers(HttpMethod.POST, "api/posts").permitAll()
//                        .requestMatchers("/api/**").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/api/refreshToken").permitAll()
//                        .anyRequest().authenticated())
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//                .exceptionHandling(handle -> handle.authenticationEntryPoint(customAuthenticationEntryPoint))
//                .build();
//    }
