package com.likelion.demo.domain.comment.exception;

import com.likelion.demo.global.exception.BaseException;

public class CommentInvalidPassword extends BaseException {
    public CommentInvalidPassword() {
        super(CommentErrorCode.COMMENT_PASSWORD_INVAlID_403);
    }
}
