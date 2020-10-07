package com.example.demo.security;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Log
// Spring(springframwork)에 있는 UserDetailService를 사용함. ????
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private MemberRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        log.info("userName: " + username);

        // member가 없기 때문에 JPA로 member를 찾아 오는 것.
        Member member = repository.findByUserId(username).get(0);

        log.info("member: " + member);

        // 만약 member가 null이라면 null을 넣고, 아니라면 CustomUser에 member를 넣음.
        return member == null ? null : new CustomUser(member);
    }
}
