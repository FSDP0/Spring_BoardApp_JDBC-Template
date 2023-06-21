package com.boardapp.boardapi.board.repository;

import java.util.List;
import com.boardapp.boardapi.board.entity.Board;

public interface BoardRepository {
    List<Board> findAllBoard();

    Board findBoardById(Long id);

    int saveBoard(Board board);

    int editBoard(Long id, Board board);

    int deleteBoard(Long id);
}
