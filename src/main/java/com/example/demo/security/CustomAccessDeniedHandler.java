package com.example.demo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// AccessDeniedHandler에 등록을 하는 Handler이기 때문에 implements 해줘야 함.
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest req,
                       HttpServletResponse res,
                       AccessDeniedException accessDeniedException)
            throws IOException, ServletException {

        /* Error Handling & info */
        ErrorInfoApi errorInfoApi = new ErrorInfoApi();
        errorInfoApi.setMessage("Access Denied");

        ObjectMapper mapper = new ObjectMapper();

        String jsonString = mapper.writeValueAsString(errorInfoApi);
        res.setContentType("application/json;charset=UTF-8");
        // 접근 권한을 주는 것이니까 403의 상태를 줘야 함.
        res.setStatus(403);
        res.getWriter().write(jsonString);
        // 사용자가 비밀번호 잘못 입력 혹은 정상적인 접근 하는데 (Auth는 정상) Auth 차원에서 공격할 수 있음.

    }
}
