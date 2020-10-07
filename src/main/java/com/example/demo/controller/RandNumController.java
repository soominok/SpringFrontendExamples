package com.example.demo.controller;

import com.example.demo.entity.RandNumMessage;
import com.example.demo.service.RandNumService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// "*" 모든 것을 받겠습니다.(수행하겠습니다)
@Controller
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class RandNumController {
    static final Logger log = LoggerFactory.getLogger(RandNumController.class);

    @Autowired
    private RandNumService randnumService;

    @GetMapping("/random")
    // ResponseEntity: 현재 동작의 성공 여부와 json 형태의 데이터 넘겨주는 역할 (json데이터가 넘어간다는 것!)
    // Controller를 썼기 때문에 ResponseBody 필요.
    // RestController를 쓰면 ResponseBody 안써도 됨.
    @ResponseBody
    public ResponseEntity<RandNumMessage> getRandom() {
        log.info("It's operate!");
        RandNumMessage random = randnumService.getRandom();
        // html로 받은게 아니라 ResponseEntity로 받아서 처리했기 때문에 홈페이지 화면 상에서는 status=500으로 오류가 뜨는 것.)
        // Vue에서 화면 처리를 해줘야 함. (Vue->src->views->Test.vue 페이지에서 작성)
        return ResponseEntity.ok(random);
    }
}
