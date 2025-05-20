package com.likelion.demo.domain.comment.web.dto;

import com.likelion.demo.domain.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public record CommentSummaryRes(List<CommentSummary> commentList) {

    public record CommentSummary(
        Long commentId,
        String content,
        String username,
        LocalDateTime createAt) {

    }
}
