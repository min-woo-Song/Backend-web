package com.smw.Backend.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponse {

    private Long id;
    private String title;
    private String content;
    private String username;
    private int readCnt;
    private LocalDateTime wDate;
    private LocalDateTime uDate;
    private String isView;
}
