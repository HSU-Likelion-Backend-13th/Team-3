package com.likelion.demo.domain.post.exception;

import com.likelion.demo.global.response.code.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PostErrorCode implements BaseResponseCode {
    POST_NOT_FOUND_404("POST_NOT_FOUND_404", 404, "해당 게시글이 존재하지 않습니다.");

    private final String code;
    private final int httpStatus;
    private final String message;
}
