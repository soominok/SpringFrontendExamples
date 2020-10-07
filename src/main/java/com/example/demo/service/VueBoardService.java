package com.example.demo.service;

import com.example.demo.entity.VueBoard;
import org.springframework.stereotype.Service;

import java.util.List;

public interface VueBoardService {
    public void register(VueBoard board) throws Exception;
    public VueBoard read(Long boardNo) throws Exception;
    public void modify(VueBoard board) throws Exception;

    // remove는 객체로 지우는 것이 아니라 번호로 지우니까 Long boardNo로 적어주면 됨!
    public void remove(Long boardNo) throws Exception;
    public List<VueBoard> list() throws Exception;
}
