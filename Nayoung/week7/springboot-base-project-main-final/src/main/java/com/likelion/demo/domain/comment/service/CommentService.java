package com.likelion.demo.domain.comment.service;

import com.likelion.demo.domain.comment.web.dto.CommentDetailRes;
import com.likelion.demo.domain.comment.web.dto.CommentSummeryRes;
import com.likelion.demo.domain.comment.web.dto.CreateCommentReq;
import com.likelion.demo.domain.comment.web.dto.CreateCommentRes;
import org.springframework.web.bind.annotation.PathVariable;

public interface CommentService {
    CreateCommentRes create(Long postId, CreateCommentReq createCommentReq);
    CommentSummeryRes getAllComment(Long postId);
    CommentDetailRes getComment(Long postId, Long commentId);
}
