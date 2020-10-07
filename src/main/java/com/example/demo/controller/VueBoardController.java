package com.example.demo.controller;

import com.example.demo.entity.VueBoard;
import com.example.demo.service.VueBoardService;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

// lombok 때문에 @Log를 써줌.
@Log
@RestController
@RequestMapping("/boards")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class VueBoardController {
    // static final Logger log = LoggerFactory.getLogger(VueBoardController.class);
    @Autowired
    private VueBoardService service;

    @GetMapping("/{boardNo}")
    // entity에 있는 내용을 가져오는 것. (기존에 Board entity 재활용해도 되지만 헷갈리니까 entity에 VueBoard를 새로 생성해주기)
    public ResponseEntity<VueBoard> read(
            @PathVariable("boardNo") Long boardNo) throws Exception {
        log.info("read");

        // service에서 읽어온 정보를 뿌리세요.
        VueBoard board = service.read(boardNo);
        System.out.println("VueBoardController: " + board);

        // 보내야 하는 정보 - 객체 VueBoard로 지정해주고 상태 정보르 ok로 보내주면 됨.
        return new ResponseEntity<VueBoard>(board, HttpStatus.OK);
    }

    // board 치면 나오는 녀석
    // 이 부분이 Vue에서 actions.js에 fetchBoardList에 axios로 연결되서 list를 불러오게 되는 것!
    @GetMapping("")
    public ResponseEntity<List<VueBoard>> list() throws Exception {
        log.info("list()");

        // Vue에서 axios 통해서 이쪽으로 날아가는 것.
        return new ResponseEntity<>(service.list(), HttpStatus.OK);
    }

    @PostMapping("")
    // 등록한 건 String이 아니니까 VueBoard로 고쳐줘야 함.
    // 진짜 DB에서 VueBoard를 꺼내서 처리를 해주는 거니까 String 처리해주면 안됨!!
    public ResponseEntity<VueBoard> register(
            // Json 정보로 날아오는지를 확인해 볼 수 있음.
            @Validated @RequestBody VueBoard board,
            UriComponentsBuilder uriBuilder) throws Exception {
        log.info("POST register()");

        // register 등록
        service.register(board);

        // register 등록하고 나서 Vue Board에서 entity 만들어서 넣어야 함.
        log.info("register board.getBoarNo() = " + board.getBoardNo());

        /*
        URI resourceURI = uriBuilder.path("boards/{boardNo}")
                .buildAndExpand(board.getBoardNo())
                .encode()
                .toUri();
        return ResponseEntity.created(resourceURI).build();
         */

        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    // Delete하는 작업 처리하는 것
    @DeleteMapping("/{boardNo}")
    // 지우는 대상은 있지만 남아서 return하는 것은 없기 때문에 void
    public ResponseEntity<Void> remove(
            @PathVariable("boardNo") Long boardNo) throws Exception {
        log.info("remove");

        service.remove(boardNo);

        // 다 지우고 나서는 status에 아무것도 없다는 상태 표시해줌. (지울 때 쓰는 패턴)
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{boardNo}")
    // <VueBoard>로 해줘야 함!!
    // 처리 결과가 어떻게 되었는지 지정해줘야 하는데 void로 지정해놔서 아무것도 return이 안되는 것!!
    public ResponseEntity<VueBoard> modify(
            @PathVariable("boardNo") Long boardNo,
            @Validated @RequestBody VueBoard board) throws Exception {
        // 디버깅 확인을 위해 "Put - modify()"로 수정해보기!
        log.info("Put - modify()");
        // 변경 정보를 가지고 들어오는지 확인해보기!!
        System.out.println(board);

        board.setBoardNo(boardNo);
        service.modify(board);

        // 수정을 했으니까 수정된 정보를 보내주고 수정완료 되었다는 표시를 보내줌.
        return new ResponseEntity<>(board, HttpStatus.OK);
    }
}
