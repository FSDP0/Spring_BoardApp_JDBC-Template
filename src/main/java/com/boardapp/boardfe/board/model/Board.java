package com.boardapp.boardfe.board.model;

import java.util.Date;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Board {
    private Long num;
    private String title;
    private String writeName;
    private String contents;
    private Date writeDate;
    private Date modifyDate;

    @Builder
    public Board(Long num, String title, String writeName, String contents, Date writeDate,
            Date modifyDate) {
        this.num = num;
        this.title = title;
        this.contents = contents;
        this.writeName = writeName;
        this.writeDate = writeDate;
        this.modifyDate = modifyDate;
    }
}
