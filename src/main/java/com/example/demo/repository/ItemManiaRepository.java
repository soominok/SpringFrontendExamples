package com.example.demo.repository;


import com.example.demo.entity.ItemMania;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ItemManiaRepository {
    static final Logger log =
            LoggerFactory.getLogger(ItemManiaRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void create(ItemMania itemmania) throws Exception {
        log.info("Repository create()");

        String query = "insert into itemmania(id, pw, nickname, item, price) values(?, ?, ?, ?, ?)";

        jdbcTemplate.update(query, itemmania.getId(), itemmania.getPw(), itemmania.getNickname(), itemmania.getItem(), itemmania.getPrice());
    }

    public List<ItemMania> list() throws Exception {
        log.info("Repository list()");

        List<ItemMania> results = jdbcTemplate.query(
                "select itemmania_no, id, pw, nickname, item, price from itemmania " +
                        "where itemmania_no > 0 order by itemmania_no desc",
                new RowMapper<ItemMania>() {
                    @Override
                    public ItemMania mapRow(ResultSet rs, int rowNum) throws SQLException {
                        ItemMania itemmania = new ItemMania();
                        itemmania.setItemmaniaNo(rs.getInt("itemmania_no"));
                        itemmania.setId(rs.getString("id"));
                        itemmania.setPw(rs.getString("pw"));
                        itemmania.setNickname(rs.getString("nickname"));
                        itemmania.setItem(rs.getString("item"));
                        itemmania.setPrice(rs.getInt("price"));
                        return itemmania;
                    }
                });
            return results;
    }

    /*public Board read(Integer boardNo) throws Exception {
        // 특정한 board 번호를 가지고 번호, 제목, 내용, 저자, 등록일을 가져오는 작업
        List<Board> results = jdbcTemplate.query(
                // ? 는 우리가 여기에 값을 지정해 주겠다는 얘기 (boardNo)
                "select board_No, title, content, writer, " +
                        "reg_date from board where board_no = ?",
                new RowMapper<Board>() {
                    @Override
                    public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Board board = new Board();

                        board.setBoardNo(rs.getInt("board_no"));
                        board.setTitle(rs.getString("title"));
                        board.setWriter(rs.getString("writer"));
                        board.setContent(rs.getString("content"));
                        board.setRegDate(rs.getDate("reg_date"));

                        return board;
                    }
                    // ? 에 boardNo가 들어감을 지정 함.
                }, boardNo
        );

    public ItemMania read(Integer itemmaniaNo) throws Exception {


        }*/

    public void remove(Integer itemmaniaNo) throws Exception {
        String query = "delete from itemmania where itemmania_no = ?";
        jdbcTemplate.update(query, itemmaniaNo);
    }



}
