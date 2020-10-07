package com.example.demo.security;

import com.example.demo.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.stream.Collectors;

// Jwt를 쓰는 것이니까 좀 복잡함.
// User에 넣어줘야 함 . security 안에 User가 있는데 이것을 사용해주는 이유는
// GrantedAuthority와 SimpleGrantedAuthority가 User안에 들어있기 때문에 사용할 수 있는 것이기 때문임!!
public class CustomUser extends User {
    private Member member;

    // Collection는 자료구조였음.
    // 데이터 타입이 뭐가 올지 모르지만 GrantedAuthority로 상속한다는 의미.
    public CustomUser(String username, String password,
                      Collection<? extends GrantedAuthority> authorities) {
        // 상속을 받으니까 위쪽으로 정보를 전달해줘야 함.
        super(username, password, authorities);
    }

    public CustomUser(Member member) {
        super(member.getUserId(), member.getUserPw(),
                member.getAuthList().stream()
                        .map(auth -> new SimpleGrantedAuthority(auth.getAuth()))
                        .collect(Collectors.toList()));

        // new SimpleGrantedAuthority여기에 원하는 정보를 넣고, for문 돌려도 됨
        // 데이터 속도 빠르게 처리하기 위해서 맵이랑 stream을 쓴 것!

        this.member = member;
    }

    // 구지 lombok을 쓸 필요 없음.
    public Member getMember() {
        return member;
    }
    // 람다 => 권한들을 맴에 넣고 list로 정리하는 것
    // 각각의 가지고 있는 auth(사용자 권한 정보)가 맵핑되는 것.
    // 사용자 권한인지 관리자 권한인지 등등을 확인 ??????????
}
