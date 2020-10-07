package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

// 게시판 만들 때 DB 연동시키려고 생성해줌.
// Configuration은 설정파일임을 명시하는 부분이다.
// 몇몇 특정 지정된 키워드들을 제외한 사용자 커스텀 클래스의 경우엔
// 설정 파일임을 명시해줄 필요가 있다.
@Configuration
public class DBConfig {
    // Spring 프레임워크에서 관리하는 객체를 Bean이라 한다.
    @Bean
    public DataSource dataSource() {
        // -> JPA를 사용하기 위한 목적으로 설정하는 것들
        // MYSQL의 사용자 비번 구동 드라이버 위치등을 지정하면 된다. <-
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        // application.yaml에 있는 내용을 적어주면 됨.
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/testdb?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=utf8");
        dataSource.setUsername("bitai");
        dataSource.setPassword("456123");
        return dataSource;
    }
}
