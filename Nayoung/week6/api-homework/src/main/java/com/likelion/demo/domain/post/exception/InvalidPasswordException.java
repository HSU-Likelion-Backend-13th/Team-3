package com.likelion.demo.domain.post.exception;

import com.likelion.demo.global.exception.BaseException;

public class InvalidPasswordException extends BaseException {
    public InvalidPasswordException() {
        super(PostErrorCode.POST_PASSWORD_INVALID_403);
    }
}
