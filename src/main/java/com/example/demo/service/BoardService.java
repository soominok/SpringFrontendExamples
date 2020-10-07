package com.example.demo.service;

import com.example.demo.entity.Board;

import java.util.List;

// 인터페이스를 사용하는 이유: 다형성
// A - test(), B - test(), C - test()
// 각각의 이름을 통일하면서 사용 방식은 다르게 사용
// 데이터 처리의 일관성을 제공해줄 수 있다.
public interface BoardService {
    // throws Exception은
    // DB를 처리할 때 오류가 발생할 수 있으므로
    // 예외 처리 루틴에게 해당 부분의 처리를 위임
    public void register(Board board) throws Exception;

    public List<Board> list() throws Exception;

    // 인터페이스에 내용 추가해줬으니까 해당 매서드에 대한 인터페이스를 작성해줘야 함.(구현해줘야 함)
    public Board read(Integer boardNo) throws Exception;

    // remove는 returen할 값이 없기 때문에 void 사용
    public void remove(Integer boardNo) throws Exception;

    public void modify(Board board) throws Exception;
}
// interface를 사용하려면 구현(implements)를 해줬어야 함

// (다시 생각하기) 읽은 결과를 보여주고 목록 자체를 보여줘야 하니까