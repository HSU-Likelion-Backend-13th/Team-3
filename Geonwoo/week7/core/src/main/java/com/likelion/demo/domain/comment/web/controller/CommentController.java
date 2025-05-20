package com.likelion.demo.domain.comment.web.controller;

import com.likelion.demo.domain.comment.web.dto.*;
import com.likelion.demo.domain.comment.service.CommentService;
import com.likelion.demo.global.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/post/{postId}/comment")
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 생성
    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createComment(
            @PathVariable Long postId,
            @RequestBody @Valid CreateCommentReq createCommentReq
    ) {
        CreateCommentRes createCommentRes = commentService.createComment(postId, createCommentReq);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(SuccessResponse.created(createCommentRes));
    }

    // 댓글 전체 조회
    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getAllComment(@PathVariable Long postId) {
        CommentSummaryRes res = commentService.getAllComment(postId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.ok(res));
    }

    // 댓글 단일 조회
    @GetMapping("/{commentId}")
    public ResponseEntity<SuccessResponse<?>> getComment(@PathVariable Long postId, @PathVariable Long commentId) {
        CommentDetailRes createCommentRes = commentService.getComment(postId, commentId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.ok(createCommentRes));
    }

    // 댓글 수정
    @PutMapping("/{commentId}")
    public ResponseEntity<SuccessResponse<?>> modifyComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @RequestBody ModifyCommentReq modifyCommentReq
    ) {
        CommentDetailRes commentDetailRes = commentService.modifyComment(postId, commentId, modifyCommentReq);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.ok(commentDetailRes));
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<SuccessResponse<?>> deleteComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @RequestBody DeleteCommentReq deleteCommentReq
    ) {
        commentService.deleteComment(postId, commentId, deleteCommentReq);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.empty());
    }
}
