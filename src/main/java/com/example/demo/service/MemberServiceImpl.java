package com.example.demo.service;

import com.example.demo.entity.Member;
import com.example.demo.entity.MemberAuth;
import com.example.demo.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Autowired
    private MemberRepository repository;

    @Override
    public void register(Member member) throws Exception {
        // 다시 보기~~
        Member memEntity = new Member();
        // getter, setter는 안만들었지만 lombok이 컨트롤 하는 것!!
        memEntity.setUserId(member.getUserId());
        memEntity.setUserPw(member.getUserPw());
        memEntity.setUserName(member.getUserName());
        memEntity.setJob(member.getJob());

        MemberAuth memberAuth = new MemberAuth();
        memberAuth.setAuth("ROLE_MEMBER");

        memEntity.addAuth(memberAuth);

        repository.save(memEntity);

        member.setUserNo(memEntity.getUserNo());

    }

    @Override
    public void setupAdmin(Member member) throws Exception {
        Member memEntity = new Member();
        memEntity.setUserId(member.getUserId());
        memEntity.setUserPw(member.getUserPw());
        memEntity.setUserName(member.getUserName());
        memEntity.setJob(member.getJob());

        MemberAuth memberAuth = new MemberAuth();
        memberAuth.setAuth("ROLE_ADMIN");

        memEntity.addAuth(memberAuth);

        repository.save(memEntity);
    }

    @Override
    public Member read(Long userNo) throws Exception {
        log.info("Member Service read()");
        return repository.getOne(userNo);
    }

    @Override
    public void modify(Member member) throws Exception {
        Member memEntity = repository.getOne(member.getUserNo());
        memEntity.setUserName(member.getUserName());
        memEntity.setJob(member.getJob());

        // member는 권한을 수정해주는 작업.
        List<MemberAuth> memberAuthList = memEntity.getAuthList();
        List<MemberAuth> authList = member.getAuthList();

        for(int i = 0; i < authList.size(); i++) {
            MemberAuth auth = authList.get(i);

            // 크기가 작다면 더이상 할 필요가 없음.
            if(i < memberAuthList.size()) {
                MemberAuth memberAuth = memberAuthList.get(i);
                // 얻어와서 memberAuth에 auth.getAuth()를 셋팅
                memberAuth.setAuth(auth.getAuth());
            }
        }

        // 저장한다.
        repository.save(memEntity);
    }

    @Override
    public void remove(Long userNo) throws Exception {

        // deleteById는 JPA가 서포트하는 매서드 중 하나임.
        repository.deleteById(userNo);
    }

    @Override
    public List<Member> list() throws Exception {
        // 모든 멤버를 가져옴.
        List<Object[]> valArrays = repository.listAllMember();
        List<Member> memberList = new ArrayList<Member>();

        for(Object[] valArr : valArrays) {
            Member mem = new Member();

            mem.setUserNo((Long) valArr[0]);
            mem.setUserId((String) valArr[1]);
            mem.setUserPw((String) valArr[2]);
            mem.setUserName((String) valArr[3]);
            mem.setJob((String) valArr[4]);
            mem.setRegDate((Date) valArr[5]);

            memberList.add(mem);
        }

        return memberList;
    }


    @Override
    public long countAll() throws Exception {
        return repository.count();
    }
}
