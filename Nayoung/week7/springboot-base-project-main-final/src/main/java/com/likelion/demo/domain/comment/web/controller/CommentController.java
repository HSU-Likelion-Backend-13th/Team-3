package com.likelion.demo.domain.comment.web.controller;

import com.likelion.demo.domain.comment.service.CommentService;
import com.likelion.demo.domain.comment.web.dto.CommentDetailRes;
import com.likelion.demo.domain.comment.web.dto.CommentSummeryRes;
import com.likelion.demo.domain.comment.web.dto.CreateCommentReq;
import com.likelion.demo.domain.comment.web.dto.CreateCommentRes;
import com.likelion.demo.global.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post/{postId}/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createComment(@PathVariable Long postId, @RequestBody @Valid CreateCommentReq createCommentReq) {
        CreateCommentRes res = commentService.create(postId, createCommentReq);

        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessResponse.created(res));
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getAllComments(@PathVariable Long postId) {
        CommentSummeryRes res = commentService.getAllComment(postId);

        return ResponseEntity.status(HttpStatus.OK).body(SuccessResponse.ok(res));
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<SuccessResponse<?>> getComment(@PathVariable Long postId, @PathVariable Long commentId) {
        CommentDetailRes res = commentService.getComment(postId, commentId);
        return ResponseEntity.status(HttpStatus.OK).body(SuccessResponse.ok(res));
    }
}
