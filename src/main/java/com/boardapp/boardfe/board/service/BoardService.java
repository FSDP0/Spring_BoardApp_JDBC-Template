package com.boardapp.boardfe.board.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.boardapp.boardfe.board.client.BoardClient;
import com.boardapp.boardfe.board.model.Board;

@Service
public class BoardService {
    private final BoardClient boardClient;

    public BoardService(BoardClient boardClient) {
        this.boardClient = boardClient;
    }

    public List<Board> findAllBoard() {
        return this.boardClient.getAllBoards();
    };

    public Board findByBoardId(Long id) {
        return this.boardClient.getBoardById(id);
    }

    public String createBoard(Board boardDto) {
        Board board = Board.builder().title(boardDto.getTitle()).contents(boardDto.getContents())
                .writeName(boardDto.getWriteName()).build();

        return this.boardClient.createBoardPost(board);
    }

    public String editBoard(Board boardDto) {
        Board board = Board.builder().num(boardDto.getNum()).title(boardDto.getTitle())
                .writeName(boardDto.getWriteName()).contents(boardDto.getContents()).build();

        return this.boardClient.editBoardPost(board.getNum(), board);
    }


    public String deleteBoard(Long id) {
        return this.boardClient.deleteBoardPost(id);
    }
}
