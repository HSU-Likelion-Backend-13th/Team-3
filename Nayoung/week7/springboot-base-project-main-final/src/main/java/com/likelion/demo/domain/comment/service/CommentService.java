package com.likelion.demo.domain.comment.service;

import com.likelion.demo.domain.comment.web.dto.*;


public interface CommentService {
    CreateCommentRes create(Long postId, CreateCommentReq createCommentReq);
    CommentSummeryRes getAllComment(Long postId);
    CommentDetailRes getComment(Long postId, Long commentId);

    // 댓글 수정
    CommentDetailRes modifyOne(Long postId, Long commentId, ModifyCommentReq modifyCommentReq);

    // 댓글 삭제
    void deleteOne(Long postId, Long commentId, DeleteCommentReq deleteCommentReq);
}
