package com.likelion.demo.domain.comment.exception;

import com.likelion.demo.global.response.code.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum CommentErrorCode implements BaseResponseCode {
    COMMENT_NOT_FOUND_404("COMMENT_NOT_FOUND_404","댓글을 찾을 수 없습니다.",404),
    COMMENT_PASSWORD_INVALID_403("COMMENT_PASSWORD_INVALID_403","비밀번호가 올바르지 않습니다.",403);


    private final String code;
    private final String message;
    private final int httpStatus;
}
