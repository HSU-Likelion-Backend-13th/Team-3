package com.likelion.demo.domain.comment.web.controller;

import com.likelion.demo.domain.comment.service.CommentService;
import com.likelion.demo.domain.comment.web.dto.*;
import com.likelion.demo.global.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post/{postId}/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createComment(@PathVariable Long postId, @RequestBody @Valid CreateCommentReq createCommentReq){
        CreateCommentRes res = commentService.create(postId,createCommentReq);

        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessResponse.created(res));

    }

    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getAllComment(@PathVariable Long postId){
        CommentSummeryRes res = commentService.getAllComment(postId);

        return ResponseEntity.status(HttpStatus.OK).body(SuccessResponse.ok(res));
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<SuccessResponse<?>> getComment(@PathVariable Long postId, @PathVariable Long commentId){
        CommentDetailRes res = commentService.getComment(postId,commentId);
        return ResponseEntity.status(HttpStatus.OK).body(SuccessResponse.ok(res));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<SuccessResponse<?>> modifyComment(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody @Valid ModifyCommentReq modifyCommentReq){
        CommentDetailRes res = commentService.modifyComment(postId,commentId,modifyCommentReq);

        return ResponseEntity.status(HttpStatus.OK).body(SuccessResponse.ok(res));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<SuccessResponse<?>> deleteComment(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody @Valid DeleteCommentReq deleteCommentReq){
        commentService.deleteComment(postId,commentId,deleteCommentReq);

        return ResponseEntity.status(HttpStatus.OK).body(SuccessResponse.empty());
    }
}
