package com.boardapp.boardapi.board.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import com.boardapp.boardapi.board.entity.Board;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcBoardRepository implements BoardRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcBoardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Board> findAllBoard() {
        String sql = "SELECT * FROM boards.board";

        List<Board> sample = this.jdbcTemplate.query(sql, boardRowMapper);

        return sample;
    }

    @Override
    public Board findBoardById(Long id) {
        String sql = "SELECT * FROM boards.board WHERE board_id = ?";

        return this.jdbcTemplate.queryForObject(sql, boardRowMapper, id);
    }

    @Override
    public int saveBoard(Board board) {
        String sql = "INSERT INTO boards.board(";
        sql += "board_title, user_id, board_content, created_date";
        sql += ") VALUES (?, ?, ?, ?)";

        int effectFlag = 0;

        effectFlag = this.jdbcTemplate.update(sql, board.getBoardTitle(), board.getBoardAuthor(),
                board.getBoardContent(), LocalDateTime.now());

        return effectFlag;
    }

    @Override
    public int editBoard(Long id, Board board) {
        String sql = "UPDATE boards.board SET ";
        sql += "board_title = ?, ";
        sql += "board_content = ?, ";
        sql += "modified_date = ? ";
        sql += "WHERE board_id = ?";

        int effectFlag = 0;

        effectFlag = this.jdbcTemplate.update(sql, board.getBoardTitle(), board.getBoardContent(),
                LocalDateTime.now(), id);

        return effectFlag;
    }

    @Override
    public int deleteBoard(Long id) {
        String sql = "DELETE FROM boards.board WHERE board_id = ?";

        return this.jdbcTemplate.update(sql, id);
    }

    private final RowMapper<Board> boardRowMapper = (resultSet, rowNum) -> {
        Board board = Board.builder().id(resultSet.getLong("board_id"))
                .title(resultSet.getString("board_title")).author(resultSet.getString("user_id"))
                .content(resultSet.getString("board_content"))
                .createdDate(resultSet.getTimestamp("created_date"))
                .modifiedDate(resultSet.getTimestamp("modified_date")).build();

        return board;
    };
}
