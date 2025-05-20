package com.likelion.demo.domain.comment.exception;

import com.likelion.demo.global.exception.BaseException;
import com.likelion.demo.global.response.code.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum CommentErrorCode implements BaseResponseCode {

    COMMENT_NOT_FOUND_404("COMMENT_NOT_FOUND_404", 404 , "댓글을 찾을 수 없습니다."),
    COMMENT_PASSWORD_INVALID_403("COMMENT_PASSWORD_INVALID_403", 403, "비밀번호가 올바르지 않습니다.");


    private final String code;
    private final int httpStatus;
    private final String message;
}
