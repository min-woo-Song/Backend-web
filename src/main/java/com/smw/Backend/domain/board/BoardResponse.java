package com.smw.Backend.domain.board;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponse {

    private Long id;
    private String email;
    private String title;
    private String content;
    private String nickname;
    private int readCnt;
    private LocalDateTime regDate;
    private String isView;
}
