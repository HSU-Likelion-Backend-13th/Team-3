package com.likelion.demo.domain.comment.web.dto;

import java.time.LocalDateTime;
import java.util.List;

public record CommentSummeryRes(List<CommentSummery> comments) {
    public record CommentSummery(
            Long commentId,
            String content,
            String username,
            String password,
            LocalDateTime createAt
    ) {}
}
