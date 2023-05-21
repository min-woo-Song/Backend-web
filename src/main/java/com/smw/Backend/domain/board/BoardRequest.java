package com.smw.Backend.domain.board;

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

    private String email;

    @NotBlank
    private String nickname;

    @NotBlank
    @Size(min=1, max=30, message = "제목은 1자 이상 30자 이하입니다")
    private String title;

    @NotEmpty
    private String content;

    public BoardRequest(String nickname, String title, String content) {
        this.nickname = nickname;
        this.title = title;
        this.content = content;
    }
}
