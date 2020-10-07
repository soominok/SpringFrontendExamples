package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
// 해쉬 코드 생성
// 유저들 고유 넘버가 해쉬코드로 생성
@EqualsAndHashCode(of = "userNo")
@ToString

// 여기서부터 lombok 기능 아니고 jpa 기능
// JPA의 장점: 쓰는게 너무 많아지는게 싫기 때문에 JPA를 쓰는 것.
// Repository를 보면 엄청 길게 다가져와서 써줘야하기 때문에 (jdbc 써서 쿼리 날리고 가져와서 객체로 만들고 return해서 값이 있냐 없냐 확인해야 함)
// 너무 귀찮고 복잡함.
// JPA를 쓰면 모든 것을 자동으로 해줌.
// 생산성 측면에서도 JPA가 좋음!!
@Entity
// create table member를 생성하는 것.
@Table(name = "member")
public class Member {
    @Id
    // userNo, userId를 셋팅할텐데
    // 민증같은
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // user_no에 숫자를 만드는데 IDENTITY 붙어있으니까 primary key가 붙는 것.가
    @Column(name = "user_no")
    // 숫자 타입은 수는 알아서 계산
    private Long userNo;

    // DB에서 Not Null 과 같은 의미
    // @NotBlank
    @Column(length = 50, nullable = false)
    private String userId;

    @Column(length = 100, nullable = false)
    private String userName;

    @Column(length = 200, nullable = false)
    private String userPw;

    @Column(length = 16, nullable = false)
    private String job;

    // 결제하려고 쓰는 것
    // private int coin;

    // 생성된 날짜, 업데이트된 날짜
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @CreationTimestamp
    private Date regDate;

    // 한개의 정보 드~~
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @UpdateTimestamp
    private Date upDate;

    // Member 와 MemberAuth(새로운 정보)를 조인시켜서 정보를 얻겠다! ~~~??
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_no")
    private List<MemberAuth> authList = new ArrayList<MemberAuth>();

    // JPA로 만들 수 없는 것은 손으로 써야 함.
    public void addAuth(MemberAuth auth) {

        authList.add(auth);
    }

    public void clearAuth() {

        authList.clear();
    }
    // 여기까지는 멤버 클래스 완성됨.
    // MemberAuth를 생성해줘야 하는데, 이건 더 간단함. (only JPA로 생성 가능)
}
