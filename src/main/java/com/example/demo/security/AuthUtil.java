package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

public class AuthUtil {
    // static을 쓰면은 new를 안해도 쓸 수 있어서 좋음!!
    // Header 정보를 날릴 때 jwt 형식의 데이터를 날려서 식별할 수 있게 해야한다.
    // String으로 날라오는 헤더 7글자를 토큰으로 받는 것??
    // 파싱된 토큰을 가지고 subject를 뽑음.
    public static long getUserNo(String header) throws Exception {
        String token = header.substring(7);

        // byte로 정보를 얻어옴!!???
        byte[] signingKey = SecurityConstants.JWT_SECRET.getBytes();

        // parsedToken은 아래의 subject까지 포함하여 디코딩된 데이터를 얻는다. (주 목적)
        // 디코딩된 토큰을 얻을 수 있음!
        Jws<Claims> parsedToken = Jwts.parser()
                .setSigningKey(signingKey)
                .parseClaimsJws(token);

        String subject = parsedToken.getBody().getSubject();
        // 여기까지 한묶음으로 생각하면 됨!! Jws<Claims>부터!!

        // String을 int로 만들어서 실제 번호로 만드는 것.
        long userNo = Integer.parseInt(subject);

        return userNo;
        // 보안때문에 이렇게 하는 것!!
        // 데이터 날리는 건 엔코딩하는 쪽에서 날리고 여기는 디코딩임!!!
    }
}