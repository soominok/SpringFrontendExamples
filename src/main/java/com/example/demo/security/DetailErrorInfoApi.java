package com.example.demo.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

// @AllArgsConstructor는 사용되는 내부 멤버 변수들을 디폴트로 만들겠다는 뜻
@AllArgsConstructor
@Getter
public class DetailErrorInfoApi {
    private String target;
    private String message;
}
