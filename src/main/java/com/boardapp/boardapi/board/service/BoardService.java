package com.boardapp.boardapi.board.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.boardapp.boardapi.board.entity.Board;
import com.boardapp.boardapi.board.model.BoardDto;
import com.boardapp.boardapi.board.repository.JdbcBoardRepository;

@Service
public class BoardService {
    private final JdbcBoardRepository jdbcBoardRepository;

    public BoardService(JdbcBoardRepository jdbcBoardRepository) {
        this.jdbcBoardRepository = jdbcBoardRepository;
    }

    public List<BoardDto> getAllBoard() {
        List<Board> boardList = this.jdbcBoardRepository.findAllBoard();

        if (boardList.isEmpty()) {
            return null;
        }

        List<BoardDto> boardDtoList = new ArrayList<BoardDto>();

        for (Board board : boardList) {
            BoardDto boardDto = BoardDto.builder().num(board.getBoardId())
                    .title(board.getBoardTitle()).writeName(board.getBoardAuthor())
                    .contents(board.getBoardContent()).writeDate(board.getCreatedDate())
                    .modifyDate(board.getModifiedDate()).build();

            boardDtoList.add(boardDto);
        }

        return boardDtoList;
    }

    public BoardDto getBoardById(Long id) {
        Board board = this.jdbcBoardRepository.findBoardById(id);

        if (board == null) {
            return null;
        }

        BoardDto boardDto = BoardDto.builder().num(board.getBoardId()).title(board.getBoardTitle())
                .writeName(board.getBoardAuthor()).contents(board.getBoardContent())
                .writeDate(board.getCreatedDate()).modifyDate(board.getModifiedDate()).build();

        return boardDto;
    }

    public int saveBoard(BoardDto boardDto) {
        return this.jdbcBoardRepository.saveBoard(boardDto.toEntity());
    }

    public int modifyBoard(Long id, BoardDto boardDto) {
        return this.jdbcBoardRepository.editBoard(id, boardDto.toEntity());
    }

    public int removeBoard(Long id) {
        return this.jdbcBoardRepository.deleteBoard(id);
    }
}
