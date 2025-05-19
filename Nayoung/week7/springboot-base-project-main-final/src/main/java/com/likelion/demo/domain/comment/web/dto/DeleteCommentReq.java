package com.likelion.demo.domain.comment.web.dto;

import jakarta.validation.constraints.NotBlank;

public record DeleteCommentReq(@NotBlank(message = "비밀번호는 필수값입니다.") String password) {
}
