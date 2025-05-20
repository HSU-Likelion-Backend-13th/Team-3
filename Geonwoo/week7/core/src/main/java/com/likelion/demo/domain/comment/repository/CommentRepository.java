package com.likelion.demo.domain.comment.repository;

import com.likelion.demo.domain.comment.entity.Comment;
import com.likelion.demo.domain.comment.web.dto.CommentSummaryRes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPostId(Long postId);
}
