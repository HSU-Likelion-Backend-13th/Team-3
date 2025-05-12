package com.likelion.demo.domain.post.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreatePostReq {
    @NotBlank(message = "게시글 제목은 비어있을 수 없습니다.")
    private String title;
    private String content;
    private String username;
    private String password;
}
