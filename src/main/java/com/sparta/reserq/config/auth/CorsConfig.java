//package com.sparta.reserq.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
//@Configuration
//public class CorsConfig {
//
//    @Bean
//    public CorsFilter corsFilter(){
//        UrlBasedCorsConfigurationSource source =new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);// 내서버가 응답할때 json을 자바스크립트에서 처리할 수 있게 할지 설정
//        config.addAllowedOrigin("*"); // 모든 ip응답허용
//        config.addAllowedHeader("*"); //모든 헤더 응답허용
//        config.addAllowedMethod("*");// 모든 post ,get등 허용
//        source.registerCorsConfiguration("/**",config);
//        return new CorsFilter(source);
//    }
//
//}
