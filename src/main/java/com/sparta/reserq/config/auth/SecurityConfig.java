package com.sparta.reserq.config.auth;

import com.sparta.reserq.core.auth.session.MyUserDetails;
import com.sparta.reserq.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final UserDetailsService userDetailsService;
//
//    public SecurityConfig(UserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/public/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginProcessingUrl("/login")
//                .usernameParameter("email")
//                .passwordParameter("password")
//                .successHandler((request, response, authentication) -> {
//                    // 로그인 성공 시 JWT 토큰 생성 및 응답에 포함
//                    String jwtToken = generateJwtToken((MyUserDetails) authentication.getPrincipal());
//                    response.getWriter().write(jwtToken);
//                    response.getWriter().flush();
//                })
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessHandler((request, response, authentication) -> {
//                    // 로그아웃 성공 시 추가 작업
//                    response.setStatus(HttpServletResponse.SC_OK);
//                });
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService);
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }
//
//    private String generateJwtToken(MyUserDetails userDetails) {
//        // JWT 토큰 생성 로직
//        // 이 부분은 위에서 언급한 JwtProvider 클래스를 사용하면 됩니다.
//        // (withSubject, withExpiresAt, withClaim 등을 활용하여 토큰을 생성)
//        // 아래는 간단한 예시로 만든 메서드일 뿐, 실제로는 더 복잡한 로직이 필요할 수 있습니다.
//        String secretKey = "yourSecretKey";
//        Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000); // 1 hour
//        return Jwts.builder()
//                .setSubject(userDetails.getUsername())
//                .setExpiration(expiration)
//                .signWith(SignatureAlgorithm.HS512, secretKey)
//                .compact();
//    }
//}
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//
//    private final UserService userService;
//    private static String secretKey = "my-secret-key-123123";
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity
//                .httpBasic().disable()
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .addFilterBefore(new JwtTokenFilter(userService, secretKey), UsernamePasswordAuthenticationFilter.class)
//                .authorizeRequests()
//                .antMatchers("/jwt-login/info").authenticated()
//                .and().build();
//    }
//}
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final CorsFilter corsFilter;
//    private final MyUserDetailsService principalDetailsService;
//
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(principalDetailsService).passwordEncoder(bCryptPasswordEncoder());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .addFilter(corsFilter)
//                .formLogin().disable()
//                .httpBasic().disable()
//                .addFilter(new MyJwtAuthorizationFilter(authenticationManager()))
//                .authorizeRequests()
//                .antMatchers("/").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
//                .antMatchers("/admin").access("hasRole('ROLE_ADMIN')")
//                .anyRequest().permitAll();
//    }
//}
//
////@Configuration
////@EnableWebSecurity //해당파일로 시큐리티를 활성화
////@RequiredArgsConstructor
////public class SecurityConfig extends WebSecurityConfigurerAdapter {
////
////
////    private final CorsFilter corsFilter;
////    private final PrincipalDetailsService principalDetailsService;
////    @Bean
////    public BCryptPasswordEncoder bCryptPasswordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
////
////    @Bean
////    @Override
////    public AuthenticationManager authenticationManagerBean() throws Exception {
////        return super.authenticationManagerBean();
////    }
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(principalDetailsService).passwordEncoder(bCryptPasswordEncoder());
////    }
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        //http.addFilterBefore(new MyFilter1(),BasicAuthenticationFilter.class);
////        http.csrf().disable();
////        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////                .and()
////                .addFilter(corsFilter)//@CrossOrigin(인증X),시큐리티 필터에 등록인증(O)
////                .formLogin().disable()
////                .httpBasic().disable()
////                .addFilter(new JwtAuthenticationFilter(authenticationManager())) //AuthenticationManger
////                .authorizeRequests()
////                .antMatchers("/")
////                .access("hasRole('ROLE_USER')or hasRole('ROLE_Admin')")
////                .antMatchers("/admin")
////                .access("hasRole('ROLE_Admin')")
////                .anyRequest().permitAll();
////
////
////    }
////}
//
////private final JwtTokenProvider jwtTokenProvider;
//
////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        http
////                .httpBasic().disable()
////                .csrf().disable()
////                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////                .and()
////                .authorizeRequests()
////                .antMatchers("/members/login").permitAll()
////                .antMatchers("/members/test").hasRole("USER")
////                .anyRequest().authenticated()
////                .and()
////                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
////        return http.build();
////    }
//
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
////    }
////}