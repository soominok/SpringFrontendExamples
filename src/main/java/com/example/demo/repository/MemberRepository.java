package com.example.demo.repository;

import com.example.demo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    public List<Member> findByUserId(String userId);

    // 일부는 자동으로 selecting 못하는 정보도 있어서 이런 경우는 직접 지정해줘야 함.
    // 이 4가지 정보를 가져오겠다는 것.
    @Query("select m.userNo, m.userPw, m.userName, m.regDate from Member m")

    // 어떤 정보가 날아올지 모르니까 object로 해서 싹다 가져오는 것.
    // 예전에 list 가져올 때 repository에서 길게 가져왔어야 했는데
    // JPA를 사용하니까 엄청 간단해졌음!!

    // 회사에서 서로 다른 DB를 사용할 것임 (MySQL, 오라클, MongoDB, 마리아 DB... 등)
    // DB에 따라서 jdbc 등을 쓰면 DB 종류에 따라서 query문이 달라져아햠.
    // JPA를 쓰면 query 문 동일하게 사용할 수 있다는 점이 가장 큰 좋은 점!!!!!!(유지보수성, 생산성 높아짐!!)
    public List<Object[]> listAllMember();

}
