package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// localhost:8080/test 치면 Spring Test Check 라는 글자가 나타남.

// RestController는 어떤 데이터든 json으로 처리하는 것이 베이스임
// return 문자열하면 그냥 화면상에는 덩그러니 글자 나옴
// 만약 페이지 자체를 핸들링 한다면 보편적으로는 Maincontroller에서 사용하는 방식으로 씀.
@RestController
public class RestTestController {
    @RequestMapping("/test")
    public String test() {
        return "Spring Test Check";
    }
}