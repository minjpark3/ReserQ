package com.sparta.reserq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity //해당파일로 시큐리티를 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http.csrf().disable();
//        http.authorizeRequests()
//                .antMatchers("/", "/user/**","/mail/**", "/post/**", "/follower/**, /comment/**","/api/**").authenticated()
//                .anyRequest()
//                .permitAll()
//                .and()
//                .formLogin()
//                .loginPage("/auth/signin")//get
//                .loginProcessingUrl("/auth/signin")//post->스프링 시큐리티가 로그인 프레세스 진행
//                .defaultSuccessUrl("/");
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/user/**", "/mail/**", "/post/**", "/follower/**", "/comment/**", "/api/**")
                .authenticated()
                .antMatchers("/mail").permitAll() // /mail 엔드포인트에 대한 예외 추가
                .anyRequest()
                .permitAll();
    }
}