package com.example.demo.controller;

import com.example.demo.entity.Music;
import org.apache.commons.io.IOUtils;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Controller가 사용자 요청 받아서 처리해줌.
// URL 요청 들어왔을 때 처리한다는 공통점은 있음.
// RestController : json 형태로 처리 (json 핸들링)
// Controller : 일반 HTML 형식으로 처리 (html 핸들링)

// (정리)
// @Controller는 HTML 핸들링에 특화되어 있으므로
// 데이터를 처리하고자 할 경우 ResponseEntity를 활용해줘야 한다.
// @RestController는 json 형식으로 데이터 처리에 특화되어 있으므로
// HTML을 처리하고자 할 겨웅 ModelAndView를 활용해야 한다.
// ResponseEntity와 ModelAndView를 쓰면 코드 쓸게 많으니까 가장 최적의 코드로 활용할 수 있는 것을 선택해서 쓰면 됨!
@RestController
@RequestMapping("musics")
public class MusicController {
    final static Logger log =
            LoggerFactory.getLogger(MusicController.class);

    @GetMapping("")
    public ModelAndView musicIndex() {
        log.info("musicIndex()");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("music/musicIndex");

        return modelAndView;
    }

    @GetMapping("/test")
    public Music musicTest() {
        log.info("musicTest()");

        Music music = new Music();

        return music;
    }

    @GetMapping("/play")
    public ModelAndView musicPlay() {
        log.info("musicPlay()");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("music/musicPlay");

        return modelAndView;
    }


    @GetMapping("/menu")
    public ModelAndView musicMenu() {
        log.info("musicMenu()");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("music/musicMenu");

        return modelAndView;
    }

    @GetMapping("/record")
    public ModelAndView musicRecord() {
        log.info("musicRecord()");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("music/musicRecord");

        return modelAndView;
    }

    @GetMapping("/titles")
    public List<Music> musicTitles() {
        log.info("musicTitles()");

        List<Music> list = new ArrayList<Music>();

        Music music1 = new Music();
        list.add(music1);

        Music music2 = new Music();
        list.add(music2);

        return list;
    }

    // 키 값과 밸류 값 나누기 (Hash Map 사용)
    @GetMapping("/artists")
    public Map<String, Music> musicArtists() {
        log.info("musicArtists()");

        Map<String, Music> map = new HashMap<String, Music>();

        Music music1 = new Music();
        // Map에 다 정보 집어넣기 => put 사용!
        map.put("Jiha", music1);

        Music music2 = new Music();
        map.put("ABC", music2);

        return map;

    }

    @GetMapping("/resptest")
    public ResponseEntity<Void> musicRespTest() {
        log.info("musicRespTest");

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/respstring")
    public ResponseEntity<String> musicRespString() {
        log.info("musicRespString()");

        // localhost:8080에 들어가서 확인해보면 Success 메세지가 뜰 것!
        // http 핸들링할게 많다면 Controller 사용.
        // -> 가끔은 데이터도 처리해야 한다면, ResponseEntity 사용
        // 데이터 핸들링할게 엄청 많으면 RestController 사용.
        // -> 그런데 html도 조금 손 봐야할게 있다면 ModelAndView 사용!!
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    // Controller는 json처리를 안하기 때문에 ResponseEntity를 사용하면 됨.
    @GetMapping("/respclass")
    public ResponseEntity<Music> musicRespClass() {
        log.info("musicrespClass()");

        Music music = new Music();

        return new ResponseEntity<Music>(music, HttpStatus.OK);
    }

    // ResponseEntity를 사용해 보기
    // RestController는 데이터 핸들링하기 편하고
    // Controller는 html 핸들링하기 편하다.
    // 결국 결과는 동일하다!

    @GetMapping("/resplist")
    public ResponseEntity<List<Music>> musicRespList() {
        log.info("musicRespList()");

        List<Music> list = new ArrayList<Music>();

        Music music1 = new Music();
        list.add(music1);

        Music music2 = new Music();
        list.add(music2);

        return new ResponseEntity<List<Music>>(list, HttpStatus.OK);
    }

    // 이미지 파일은 byte 사용의 유의하기!
    // 이미지는 행렬데이터로 되어 있기 때문에 행렬 데이터는 byte로 사용해줘야 함!
    @GetMapping("respfile")
    public ResponseEntity<byte[]> musicImgFile()
    // 밑에 Exception e를 적어줬기 때문에 throws Exception을 해줘야 함.
                                                throws Exception {
        log.info("musicImgFile()");

        InputStream in = null;
        ResponseEntity<byte[]> entity = null;

        try {
            HttpHeaders headers = new HttpHeaders();

            in = new FileInputStream("/home/bitai/proj/testRepoLSH/test_image_2.jpg");
            headers.setContentType(MediaType.IMAGE_JPEG);

            entity = new ResponseEntity<byte[]>(
                    IOUtils.toByteArray(in),
                    headers,
                    HttpStatus.CREATED
            );
        } catch(Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
        } finally {
            in.close();
        }

        return entity;
    }
}