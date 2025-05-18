package com.likelion.demo.domain.post.exception;

import com.likelion.demo.global.response.code.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
// 게시글 관련 예외 코드를 정의하는 enum 클래스
public enum PostErrorCode implements BaseResponseCode {
    // 게시글이 존재하지 않을 때 발생하는 예외 정보
    POST_NOT_FOUND_404("POST_NOT_FOUND_404", 404, "해당 게시글이 존재하지 않습니다."),
    POST_PASSWORD_INVALID_403("POST_PASSWORD_INVALID_403", 403, "비밀번호가 일치하지 않습니다.");
    private final String code; // 에러 코드 식별용 문자열
    private final int httpStatus; // http 상태 코드
    private final String message; // 클라이언트에게 전달할 사용자 친화적인 에러 메시지
}
