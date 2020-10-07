package com.example.demo.security;

public class SecurityConstants {
    // 인증을 요청할 때 보내는 URL은 "/api/authenticate"로 fix함.
    public static final String AUTH_LOGIN_URL = "/api/authenticate";

    // http://www.allkeysgenerator.com
    // "Encryption key"에서 생성 가능 (여기서는 512-bit로 생성했음)
    public static final String JWT_SECRET = "n2r5u8x/A%D*G-KaPdSgVkYp3s6v9y$B&E(H+MbQeThWmZq4t7w!z%C*F-J@NcRf";

    // JWT 기본 토큰
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "secure-api";
    public static final String TOKEN_AUDIENCE = "secure-app";
}
