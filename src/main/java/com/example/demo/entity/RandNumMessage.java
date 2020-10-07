package com.example.demo.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

// DB에서 꺼내오는 것이 아니니까 Repository를 붙일 필요 없음
public class RandNumMessage {
    private final static Logger log =
            LoggerFactory.getLogger(RandNumMessage.class);

    static Random random = new Random();

    private Integer randNumber;

    // 생성자 만들기
    // nextInt : 0 ~ 32 전까지 랜덤한 값 가져오는 것.
    public RandNumMessage() {
        this.randNumber = random.nextInt(32) + 3;
        log.info("RandomMessage(): " + randNumber);
    }

    public Integer getRandNumber() {
        return randNumber;
    }
}
