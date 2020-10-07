package com.example.demo.entity;

// 다발로 받으려면 조금 업무량이 많더라도 list로 데이터를 받는 것이 좋음.
// 상품 페이지를 만든다고 하면, entity는 상품(product)임
// entity 패키지 안에 production이라는 class를 만들면 됨.

// 만약 회원정보 페이지라고 한다면, entity는 회원임
// 여기서 음악이니까 음악 관련 정보들이 entity
// B2B 사업한다하면, entity에 업체명 등의 정보!

// 핵심 업무 규칙이 entity
public class Music {
    private String Writer = "sdr4linux";
    private String title = "go go go";

    public String getWriter() {
        return Writer;
    }
    public String getTitle() {
        return title;
    }

}
