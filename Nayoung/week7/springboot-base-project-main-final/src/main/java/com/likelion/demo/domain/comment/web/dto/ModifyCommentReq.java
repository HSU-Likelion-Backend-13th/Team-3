package com.likelion.demo.domain.comment.web.dto;

import jakarta.validation.constraints.NotBlank;


public record ModifyCommentReq(
        @NotBlank(message = "이름은 필수값입니다.")
        String username,

        @NotBlank(message = "내용은 필수값입니다.")
        String content,

        @NotBlank(message = "비번은 필수값입니다.")
        String password
) {

}
