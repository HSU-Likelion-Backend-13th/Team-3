package com.likelion.demo.domain.post.web.dto;

import com.likelion.demo.domain.post.entity.PostState;

import java.time.LocalDateTime;

public record PostDetailRes(Long id, String title, String content, String username, String password, PostState state, LocalDateTime createdAt, LocalDateTime updatedAt) {
}