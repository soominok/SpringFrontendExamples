package com.example.demo.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // -> pathPattern 인자에 해당하는 /**은 모든 파일 디렉토리 등등을 의미
        // 모든 URL을 허용하겠다는 의미.
        // 그리고 8080 서비스포트로 들어오는 요청을 인가함을 의미한다.
        // IP 통신을 하냐, MAC 통신을 하냐에 따름.
        // 특정 IP에 대한 요청을 수락하는 작업.
        // 12.0.0.1 = localhost <-
        registry.addMapping("/**")
                .allowedOrigins("http://127.0.0.1:8080")
                .allowedOrigins("http://localhost:8080");
    }
}

// Spring과 Vue를 연결하기 위한 거니까 entity, service가 필요함.
// 외부참조 허용el dkffu