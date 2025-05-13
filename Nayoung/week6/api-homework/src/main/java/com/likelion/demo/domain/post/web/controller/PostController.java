package com.likelion.demo.domain.post.web.controller;

import com.likelion.demo.domain.post.service.PostService;
import com.likelion.demo.domain.post.web.dto.*;
import com.likelion.demo.global.response.SuccessResponse;
import com.sun.net.httpserver.Authenticator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController // RESTful API를 제공하는 컨트롤러로 등록
@RequestMapping("/post") // 이 컨트롤러의 모든 API는 /post 경로로 시작하게 됨
@RequiredArgsConstructor
public class PostController {
    // 의존성부여
    private final PostService postService;

    // 게시글 작성
    @PostMapping
    public ResponseEntity<SuccessResponse<?>> CreatePost(@RequestBody @Valid CreatePostReq createPostReq) {
        // 서비스 계층 위임
        CreatePostRes createPostRes = postService.createOne(createPostReq);

        // 반환
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(SuccessResponse.created(createPostRes));
    }

    // 게시글 단건 조회
    @GetMapping("/{postId}")
    public ResponseEntity<SuccessResponse<?>> getPostById(@PathVariable Long postId) {
        // 서비스 로직
        PostDetailRes postDetailRes = postService.getById(postId);

        // 반환
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.ok(postDetailRes));
    }


    // 게시글 전체 조회
    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getAllPosts() {
        // 서비스 로직
        PostSummaryRes postSummaryRes = postService.getAll();

        // 반환
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.ok(postSummaryRes));
    }

    // 게시글 수정
    @PutMapping("/{postId}")
    public ResponseEntity<SuccessResponse<?>> modifyPost(
            @PathVariable Long postId,
            @RequestBody ModifyPostReq modifyPostReq
    ) {
        // 서비스
        PostDetailRes postDetailRes = postService.modifyOne(postId, modifyPostReq);

        // 반환
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.ok(postDetailRes));
    }

    // 게시글 삭제
}

/*
* [CreatePost]
* 의미 : 게시글을 새로 작성한다.
* 동작 : 클라이언트가 제목, 내용, 이름, 비밀번호를 JSON으로 보내면 그걸 받아서
* DB에 저장하고, 생성된 게시글의 ID를 응답해줌
* HTTP 방식 : POST
* 경로 : /post
*
* [getPostById]
* 의미 : 특정 게시글을 ID로 조회한다.
* 동작 : /post/3 처럼 ID가 URL에 포함되어 들어오면, 해당 ID에 해당하는
* 게시글을 찾아서 반환함
* HTTP 방식 : GET
* 경로 : /post/{postId}
* 예시 : /post/8
*
* [getAllPosts]
* 의미 : 모든 게시글 목록을 조회한다.
* 동작 : DB에 있는 전체 게시글을 간단한 정보(요약형)로 묶어서 리스트 형태로 응답
* HTTP 방식 : GET
* 경로 : /post
* 예시 : 프론트 게시판 목록에서 사용하는 API
* */