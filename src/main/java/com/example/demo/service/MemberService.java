package com.example.demo.service;

import com.example.demo.entity.Member;

import java.util.List;

// 이 기능을 나중에 다른 곳에서 활용할 수 있기 때문에 인터페이스로 만들어줌.
public interface MemberService {
    public void register(Member member) throws Exception;
    // 성격이 register와 같음. ???????
    // 관리자 권한 주기
    public void setupAdmin(Member member) throws Exception;

    // Member를 받아서 번호로 읽는 것이니까!!
    public Member read(Long userNo) throws Exception;
    public void modify(Member member) throws Exception;

    // 번호에 해당하는 것을 지우니까
    public void remove(Long userNo) throws Exception;
    public List<Member> list() throws Exception;
    public long countAll() throws Exception;
}
