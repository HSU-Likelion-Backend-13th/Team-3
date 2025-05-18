package com.likelion.demo.domain.comment.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommentReq {
    @NotBlank(message = "내용은 필수값입니다.")
    private String content;

    @NotBlank(message = "이름은 필수값입니다.")
    private String username;

    @NotBlank(message = "비번은 필수값입니다.")
    private String password;
}
