package com.boardapp.boardapi.board.entity;

import lombok.Builder;
import lombok.Getter;
import java.util.Date;

@Getter
public class Board {
    private Long boardId;

    private String boardTitle;
    private String boardAuthor;
    private String boardContent;

    private Date createdDate;
    private Date modifiedDate;

    @Builder
    public Board(Long id, String title, String author, String content, Date createdDate,
            Date modifiedDate) {
        this.boardId = id;
        this.boardTitle = title;
        this.boardContent = content;
        this.boardAuthor = author;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
