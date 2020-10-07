package com.example.demo.controller;

import com.example.demo.entity.Member;
import com.example.demo.entity.MemberAuth;
import com.example.demo.security.AuthUtil;
import com.example.demo.service.MemberAuthService;
import com.example.demo.service.MemberService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@Log
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class MemberController {

    @Autowired
    private MemberService service;

    @Autowired
    private MemberAuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 메세지 소스 쓰니까 Autowired 한번 더 하기
    @Autowired
    private MessageSource messageSource;

    @PostMapping("")
    private ResponseEntity<Member> register(@Validated @RequestBody Member member)
                                                                    throws Exception {
        log.info("member.getUserName(): " + member.getUserName());

        String inputPassword = member.getUserPw();
        member.setUserPw(passwordEncoder.encode(inputPassword));

        service.register(member);

        log.info("register member.getUserNo(): " + member.getUserNo());

        // 위에서 pw 셋팅한 member 넣고 status OK 넣어줌.
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    // 관리자 권한을 hasRole 해주면 됨.
    // 사용자는??
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("")
    public ResponseEntity<List<Member>> list() throws Exception {
        return new ResponseEntity<>(service.list(), HttpStatus.OK);
        // 관리자 권한 없으면 관리자 권한 먼저 만들어주고 유저 계정 만들어줘야 함.
        // 관리자 권한 없으면 일반 사용자 권한 만들 수 없음.
    }

    @GetMapping("/{userNo}")
    public ResponseEntity<Member> read(@PathVariable("userNo") Long userNo) throws Exception {
        // 사용자 정보도 읽어서 셋팅해줘야 함.
        Member member = service.read(userNo);

        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    // delete하는 것도 권한 있는 사람에게 해줘야 하니까!
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{userNo}")
    public ResponseEntity<Void> remove(@PathVariable("userNo") Long userNo) throws Exception {
        service.remove(userNo);

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{userNo}")
    public ResponseEntity<Member> modify(@PathVariable("userNo") Long userNo,
                                         @Validated @RequestBody Member member)
                                                                throws Exception {
        log.info("modify - member.getUserName(): " + member.getUserName());
        // 디버깅할 떄 보기 좋기 때문에 사용.
        log.info("modify - userNo: " + userNo);

        member.setUserNo(userNo);
        service.modify(member);

        return new ResponseEntity<>(member, HttpStatus.OK);
    }
    // Setup은 RequetMapping하기
    // PostMapping과 큰 차이는 없음.
    @RequestMapping(value  = "/setup",
                    method = RequestMethod.POST,
                    produces = "text/plain;charset=UTF-8")
    // 관리자 권한 설정
    public ResponseEntity<String> setupAdmin(@Validated @RequestBody Member member) throws Exception {

        log.info("setupAdmin: member.getUserName(): " + member.getUserName());
        log.info("setupAdmin: service.countAll(): " + service.countAll());

        // 회원 몇 명인지 체크하기 위함.
        // 회원이 0이라면 관리자부터 만들어야 함.
        if (service.countAll() == 0) {
            String inputPassword = member.getUserPw();
            // 비밀번호 암호화
            member.setUserPw(passwordEncoder.encode(inputPassword));

            member.setJob("Admin");

            service.setupAdmin(member);
            // 관리자 권한이 왔을 때 사용하는 방식
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
            // Vue 쪽에서는 AdminSetupPage.vue에서 axios.post 처리를 잘 받아오면, alert('Register Success') 출력되게 하고, Home으로 이동하게 됨.
        }

        // count 1라도 있으면 이 작업은 안함.
        // 그렇기 때문에 BAD_REQUEST를 주는 것.
        String message = messageSource.getMessage("common.cannotSetupAdmin", null, Locale.KOREAN);
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
    // 내정보 확인하기
    @GetMapping("/myinfo")
    // 권한이 있는 멤버의 정보를 보겠다.
    public ResponseEntity<MemberAuth> getMyInfo(
            @RequestHeader (name="Authorization") String header) throws Exception {
        Long userNo = AuthUtil.getUserNo(header);
        //등록된 user 번호 확인
        log.info("register userNo: " + userNo);

        MemberAuth auth = authService.read(userNo);
        log.info("auth: " + auth);

        return new ResponseEntity<>(auth, HttpStatus.OK);
    }
}
