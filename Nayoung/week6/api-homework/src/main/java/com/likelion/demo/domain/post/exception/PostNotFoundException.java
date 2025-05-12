package com.likelion.demo.domain.post.exception;

import com.likelion.demo.global.exception.BaseException;

// 게시글이 존재하지 않을 때 발생시키는 커스텀 예외 클래스
public class PostNotFoundException extends BaseException {
    // 생성자에서 미리 정의된 에러 코드 enum을 부모 클래스(BaseException)에 전달
    public PostNotFoundException() {
        super(PostErrorCode.POST_NOT_FOUND_404);
    }
}
