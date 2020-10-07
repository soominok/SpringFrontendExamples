package com.example.demo.service;

import com.example.demo.entity.VueBoard;
import com.example.demo.repository.VueBoardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

// 구현체에다 Service 걸어줘야 함.
@Service
public class VueBoardServiceImpl implements VueBoardService{
    static final Logger log = LoggerFactory.getLogger(VueBoardServiceImpl.class);
    @Autowired
    private VueBoardRepository repository;

    @Override
    public void register(VueBoard board) throws Exception {
        repository.create(board);
    }

    // boardNo를 읽는거니까 VueBoard가 아니고 boardNo을 사용.
    @Override
    public VueBoard read(Long boardNo) throws Exception {
        // 여기서는 "" 로 써줘야 함.
        log.info("VudBoardService read(boardNo): " + boardNo);
        return repository.read(boardNo);
    }

    @Override
    public void modify(VueBoard board) throws Exception {
        log.info("VueBoardService modify(board): " + board);
        repository.update(board);
    }

    @Override
    public void remove(Long boardNo) throws Exception {
        repository.delete(boardNo);
    }

    @Override
    public List<VueBoard> list() throws Exception {
        return repository.list();
    }

}
