package com.example.demo.config;

import com.example.demo.security.CustomAccessDeniedHandler;
import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.security.JwtAuthenticationFilter;
import com.example.demo.security.JwtAuthorizationFilter;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

// jwt
// Log가 factory되어 있으니까 사용.
// -> Log는 Lombok을 통해 Logger와 LoggerFactory를 자동으로 등록하게 지원한다.
@Log
// 웹 보안과 관련된 방어 기능을 활성화시킴
@EnableWebSecurity
// 전역 매소드에 보안 설정하기  위해서 걸어놓음.
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    // HttpSecurity -> go to로 보면 알 수 있음.
    // 이 구성은 어떤 URL도 요청 가능
    // 결론적으로 ROLE_USER : 사용자에 대한 액세스 토큰
    // ROLE_ADMIN : 을 걸어주면 관리자 권한으로 사이트 제한할 수 있는 권한 가지게 되는 것!
    // ls-al, chowm 사용해서 권한 변환해준 것과 같은 역할
    // interface가 2개 들어있음 (~~Builder : lombok처럼 자동으로 서포트해주는 역할)
    protected void configure(HttpSecurity http) throws Exception {
        log.info("Security Configuration");

        // 외부에서 요청 들어오는 것도 corsorigin 발생시키지 않겠다!
        // httpSecurity 자체를 내부에서 사용하겠다는 것.

        // cors : HttpSecurity에 cors가 들어있음.
        // 만약 빈 형식에 이름에 CorsFilter를 사용하게 된다면 동작하게 되는것.
        // 지금은 따로 사용하지 않으니까 디폴트 값으로 cors 사용.

        // 1) cors()
        // CorsConfigurer () : 인자가 들어오는 경우에는 좀 다르지만 아직은 별로 하는 것이 없음 (인스턴스 만드는 역할)

        // -> 이 부분을 자세히 파고 들어가면 너무 빡세지니 간단하게만 보면
        // 접근 거부, URL 요청이 올바른지, 권한을 가지고 있는지 등등을 설정하는 것이라 보면 됨.
        // (그냥 가져다 쓰도록 한다) <-
        http.cors()
                .and()
                // 사이트 간 URL 위조해서 해킹하는 것을 disable 해주는 것 (해킹 방지)
                // 2) csrf()
                .csrf().disable()
                .exceptionHandling()

                // 토큰이 없는 애들 액세스 거부
                // 접근 못하게 해주는 등록 (대신 이걸 만들어줘야 함)
                // -> 커스텀 구간 createAccessDeniedHandler <-
                .accessDeniedHandler(createAccessDeniedHandler())
                // 인증, 거부하는 필터
                // 사용자 아이디, 패스워드가 올바른 형식으로 들어오는지 확인
                // 해킹 공격을 막기 위한 올바른 포맷으로 들어오는지 확인하는 것
                // JwtAuthenticationFilter : 공격인지 아닌지는 관심없고 실제로 권한을 부여해주는 것 (사용자 권한, 관리자 권한?)
                .and()
                // 3) 필터링처리하는 세션
                // -> 커스텀 구간 JwtAuthenticationFilter <-
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                // -> 커스텀 구간 JwtAuthorizationFilter <-
                .addFilter(new JwtAuthorizationFilter(authenticationManager()))
                .sessionManagement()

                // STATELESS: 상태 정보를 저장하지 않음 (결과 정보를 저장하지 않는 것)
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    // -> 비밀번호를 암호화 하기 위한 설정 <-
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(createUserDetailsService())
                .passwordEncoder(createPasswordEncoder());
    }

    // 엔코딩을 암호화 복코딩을 복호화 하는 것.
   @Bean
   public PasswordEncoder createPasswordEncoder() {
        return new BCryptPasswordEncoder();
   }

    // 커스텀 요소 중 하나
   @Bean
    public UserDetailsService createUserDetailsService() {
        return new CustomUserDetailsService();
   }

    // -------------------------------------이렇게 묶여있음.----------------------------------------

    // 코드 분석했을 때, 최종적으로 봤던 것이 cors에서 CorsConfigurer의 생성자에서 만들어지는
    // 내부 변수(configurationSource)를 만드는 작업을 할 것임.
    // CorsConfigurationSource는 클래스!
    // Bean을 이용해서 만들면 CorsConfigurationsSource를 사용할 수 있게 되는 것!

    // -> 어떤 형식의 URL 요청이나 Header 요청이든 수용하도록 만든다. <-
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        // 상수 값으로 생성!
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // origin이나 header 어떤거든 받겠다.
        // "*" : 무엇이든 다 허용하겠다.
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        // method도 설정해서 수용하도록 만들겠다.
        config.addAllowedMethod("OPTIONS");
        // 헤드로 들어오는 것 (이미지나 영상)
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        // 보안 관련 문제는 따로 지정해주고, 나머지는 허용하겠다고 지정해줘서 조금 더 번거로움
        // CorsConfig는 더 간편하게 썼었음.
        config.setExposedHeaders(Arrays.asList("Authorization", "Content-Disposition"));

        source.registerCorsConfiguration("/**", config);

        return source;
    }

    // 접근 거부와 관련된 생성자
    // 커스텀 요소 중 하나
    @Bean
    // createAccessDeniedHandler: 접근이 거부될 때 동작할 매서드 생성해줌.
    public AccessDeniedHandler createAccessDeniedHandler() {
        // 권한 부여 잘못된게 있다면 자동으로 액세스 디나이 해주는 기능!
        return new CustomAccessDeniedHandler();

    }
}
