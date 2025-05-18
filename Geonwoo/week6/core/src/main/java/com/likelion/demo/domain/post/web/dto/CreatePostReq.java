package com.likelion.demo.domain.post.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreatePostReq {
    @NotBlank(message = "게시글 제목은 비어있을 수 없습니다.")
    private String title;

    @NotBlank(message = "게시글 내용은 비어있을 수 없습니다.")
    private String content;

    @NotBlank(message = "작성자 이름은 비어있을 수 없습니다.")
    private String username;

    @NotBlank(message = "비밀번호는 비어있을 수 없습니다.")
    private String password;
}
