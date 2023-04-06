package com.smw.Backend.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class BoardRequest {

    @NotNull
    private Long id; // 기본키

    @NotBlank
    private String username; // 작성자

    @NotBlank
    @Size(min=1, max=30, message = "제목은 1자 이상 30자 이하입니다")
    private String title; // 제목

    @NotEmpty
    private String content; // 내용

    public BoardRequest(String username, String title, String content) {
        this.username = username;
        this.title = title;
        this.content = content;
    }
}
