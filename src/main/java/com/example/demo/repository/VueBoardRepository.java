package com.example.demo.repository;

import com.example.demo.entity.VueBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class VueBoardRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // create 생성
    public void create(VueBoard board) throws Exception{
        // 우리가 지정한 값을 넣겠다.
        String query = "insert into cafe_board (title, content, writer) values (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection con)
                            throws SQLException {
                        // new String[] {"boardNo"}에서 boardNo는 board.setBoardNo(keyHolder.getKey().longValue())에 셋팅하는 값을 automatic하게 해주는 역할(autoGenKeyColNames 역할).
                        // query는 동작하고, autoGenKeyColName가 null이 아니고
                        // 동시 접속하면 스레드 문제 발생하기 때문에 동시 접속 문제는 막아줌.
                        // 준비된 Prepare query를 동작시키는 것.

                        // GeneratedKeyHolder
                        PreparedStatement ps = con.prepareStatement(query, new String[] {"boardNo"});
                        // 위치 지정을 해줘야 함.
                        ps.setString(1, board.getTitle());
                        ps.setString(2, board.getContent());
                        ps.setString(3, board.getWriter());
                        return ps;
                    }
                }, keyHolder);

        board.setBoardNo(keyHolder.getKey().longValue());
    }

    public VueBoard read(Long boardNo) throws Exception {
        // jdbc query 날리기
        List<VueBoard> results = jdbcTemplate.query(
                "select board_no, title, content, writer, reg_date " +
                        "from cafe_board where board_no = ?",
                new RowMapper<VueBoard>() {
                    @Override
                    public VueBoard mapRow(ResultSet rs, int rowNum) throws SQLException {
                        VueBoard board = new VueBoard();

                        board.setBoardNo(rs.getInt("board_no"));
                        board.setTitle(rs.getString("title"));
                        board.setContent(rs.getString("content"));
                        // db에서 writer를 얻어오는 것.
                        board.setWriter(rs.getString("writer"));
                        board.setRegDate(rs.getDate("reg_date"));

                        // entity에 @toString이 걸려있기 때문에 sout으로 확인해볼 수 있음.
                        System.out.println("VueBoardRepository: " + board);

                        return board;
                    }
                }, boardNo
        );

        return results.isEmpty() ? null : results.get(0);
    }

    public void update(VueBoard board) throws Exception {
        String query = "update cafe_board set title = ?, content = ? " +
                "where board_no = ?";
        // query에 들어갈 값들이 이 밑에 board.getTitle(), board.getContent(), board.getBoardNo() 값들임!!!
        jdbcTemplate.update(query, board.getTitle(), board.getContent(), board.getBoardNo());

    }

    public void delete(Long boardNo) throws Exception {
        String query = "delete from cafe_board where board_no = ?";
        jdbcTemplate.update(query, boardNo);
    }

    public List<VueBoard> list() throws Exception {
        List<VueBoard> results = jdbcTemplate.query(
                "select board_no, title, content, writer, reg_date from cafe_board " +
                        "where board_no > 0 order by board_no desc, reg_date desc",
                new RowMapper<VueBoard>() {
                    @Override
                    public VueBoard mapRow(ResultSet rs, int rowNum) throws SQLException {
                        VueBoard board = new VueBoard();

                        board.setBoardNo(rs.getInt("board_no"));
                        board.setTitle(rs.getString("title"));
                        board.setContent(rs.getString("content"));
                        board.setWriter(rs.getString("writer"));
                        board.setRegDate(rs.getDate("reg_date"));

                        return board;
                    }
                }
        );

        return results;
    }
}
