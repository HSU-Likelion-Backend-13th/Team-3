package com.likelion.demo.domain.post.exception;

import com.likelion.demo.global.exception.BaseException;
import com.likelion.demo.global.response.code.BaseResponseCode;

public class PostNotFoundException extends BaseException {

    public PostNotFoundException() {
        super(PostErrorCode.POST_NOT_FOUND_404);
    }
}
