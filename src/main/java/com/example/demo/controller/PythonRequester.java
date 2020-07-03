package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PythonRequester {
    final static Logger log =
            LoggerFactory.getLogger(PythonRequester.class);

    // RequestMapping value는 결국 URL 주소에 해당한다.
    // 그러므로 localhost:8080/doRequestPythonRest
    // 이것이 URL 요청으로 들어가면 아래 함수가 동작한다.
    // 아직 파이썬 서버는 없으니까 localhost:8080/doRequestPythonRest
    // 이것만 테스트해보면 >>> URL을 넣었을 때
    // doRequestPythonRest가 뜨면 됨!
    // 하지만 http://localhost:5000/dataServer 이건 전달이 안 됨
    // 5000번 띄워 놓은 서버가 없으니까!
    @RequestMapping(
            value = "/doRequestPythonRest", // 이 명령이 떨어졌을 때 localhost에 있는 5000번으로 요청을 넣음.
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET // GET 요청 하겠다는 의미
    )
    public String doRequestPythonRest() {
        log.info("doRequestPythonRest()");

        List<HttpMessageConverter<?>> converters =  // <?> : 와일드 카드
                new ArrayList<HttpMessageConverter<?>>();
        converters.add(new FormHttpMessageConverter());
        converters.add(new StringHttpMessageConverter());

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(converters);

        MultiValueMap<String, String> map =
                new LinkedMultiValueMap<String, String>();
        map.add("str", "request test");


        // Python Flask 서버의 5000번에 dataServer URL을 요청하는 작업업
        // Flask 서버가 없으니까 에러가 남!
       String result = restTemplate.getForObject(
                "http://localhost:5000/dataServer", // 이렇게 하면 요청은 보낼 수 있는데 응답은 안 옴.
                // 5000번에 있는 데이터 서버한테 요청을 보내는 것.
                // 보내려면 파이썬 베이스에서 웹서버 하나 더 띄워야 함.
                String.class
        );

        log.info("result = " + result);

        return result; // Json 데이터를 받아오는 작업
// 2020-07-03 16:26:59.610  INFO 29502 --- [nio-8080-exec-4] c.e.demo.controller.PythonRequester      : doRequestPythonRest()
// 이것으로 들어온 것이 확인 됨.




    }
}
