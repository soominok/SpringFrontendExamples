package com.example.demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

// 인증 필터 만드는 작업
// spring에서 제공하는 UsernamePasswordAuthenticationFilter를 상속 받음.
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    // 생성자를 호출해서 초기화 시켜줌.
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

        // 인자로 들어온 URL에서 정보를 보겠다!
        setFilterProcessesUrl(SecurityConstants.AUTH_LOGIN_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {

        // (Java에서)인터페이스 implements 시켜서 ~~~ -> 스프링 확장 버전
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        //  authenticationManager로 토큰을 보냄.
        return authenticationManager.authenticate(authenticationToken);
    }

    // 인증이 성공했으면 사용자 정보 필요할 것이고, 사용자가 어떤 권한 계정을 가지고 있는지에 대한 응답을 해줘야 함.
    // 스프링이 아니고 Vue에서 로그인 한다고 하면 액세스 토큰을 가지고 있어야 함(로그인 되어 있는 상태), 액세스 토큰 없으면 로그인 안되어 있는 상태로 구분할 수 있어야 함.

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication authResult)
            throws IOException, ServletException {

        CustomUser user = ((CustomUser) authResult.getPrincipal());

        // CustomUser에서 정보를 얻어오는 것.
        // 그 정보들을 list 형태로 가져오고!
        List<String> roles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        byte[] signingKey = SecurityConstants.JWT_SECRET.getBytes();

        String token = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setHeaderParam("typ", SecurityConstants.TOKEN_TYPE)
                .setIssuer(SecurityConstants.TOKEN_ISSUER)
                .setAudience(SecurityConstants.TOKEN_AUDIENCE)
                // CustomUser에 있는 getMember와 getUserNo를 가져온 것.
                .setSubject("" + user.getMember().getUserNo())
                // 3600초 => 3600000밀리 초
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .claim("rol", roles)
                .compact();

        res.addHeader(SecurityConstants.TOKEN_HEADER, SecurityConstants.TOKEN_PREFIX + token);
    }
}