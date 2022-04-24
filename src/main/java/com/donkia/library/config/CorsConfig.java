package com.donkia.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 20220424
 * jwt 적용을 위한 설정
 * 참고 : https://www.youtube.com/watch?v=q_Rc_9o6zLU&list=PL93mKxaRDidERCyMaobSLkvSPzYtIk0Ah&index=21
 * */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true); //내 서버가 응답할 때 json을 자바스크립트에서 처리할 수 있게 할지 설정
        config.addAllowedOrigin("*"); // 모든 ip에 응답을 허용
        config.addAllowedHeader("*"); // 모든 header에 응답을 허용
        config.addAllowedMethod("*"); // 모든 post,get,delete,patch 요청을 허용
        source.registerCorsConfiguration("/api/**", config); //api의 모든 주소는 해당 config 내용 적용
        return new CorsFilter(source);
    }
}
