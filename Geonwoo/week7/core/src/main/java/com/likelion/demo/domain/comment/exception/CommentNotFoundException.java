package com.likelion.demo.domain.comment.exception;

import com.likelion.demo.global.exception.BaseException;

public class CommentNotFoundException extends BaseException {
    public CommentNotFoundException() {
        super(CommentErrorCode.COMMENT_NOT_FOUND_404);
    }
}
