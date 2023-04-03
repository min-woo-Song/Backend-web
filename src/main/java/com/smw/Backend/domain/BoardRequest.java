package com.smw.Backend.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BoardRequest {

    @NotNull
    private Long id; // 기본키

    @NotBlank
    private String username; // 작성자

    @NotBlank
    private String title; // 제목

    @NotEmpty
    private String content; // 내용

    public BoardRequest(String username, String title, String content) {
        this.username = username;
        this.title = title;
        this.content = content;
    }
}
