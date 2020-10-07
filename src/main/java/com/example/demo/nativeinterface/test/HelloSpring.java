package com.example.demo.nativeinterface.test;

public class HelloSpring {
    // 이 print가 "src-hello-c-hello.c"를 호출하게 됨.
    // native에 있는 print함수를 동작시켜라 라는 의미이기 때문.

    // native의 의미 : Java에 있는 com.example.demo에 HelloSpring이라는 클래스가 있어서
    // 거기에 있는 print 함수가 대응된다는 의미
    // 대음되면 hello.c에 있는 ""Hello C Language with Java!""가 출력 됨.

    // native란 시스템 하위 API들을 활용할 경우 사용한다.
    // native 키워드가 있으면 C, C++ 코드를 사용하겠다는 의미.
    // 직접적으로 하드웨어에서 센서데이터를 긁어와서 여기에 적용하고 싶을 때에는 native를 써줘야 함.

    // Netty(JNI 베이스 고속 통신 라이브러리) -> 동영상 서비스 제공하는 곳에서 Netty 많이 사용.
    public native String print();

    static {
        // System.out.println()
        // 라이브러리: 결국 실행 파일(메모리 섹션 text의 집합 덩어리)
        // *.dll, *.so 파일들이 라이브러리 파일임
        // 리눅스, 유닉스(맥)에서는 hello라는 이름이 libhello.so와 동일 (규칙임)
        // loadLibrary로 libhello.so 라이브러리를 사용하겠습니다.
        // >>> hello.c에 있는 코드를 활용할 수 있게 됨.
        System.loadLibrary("hello");
    }
}


