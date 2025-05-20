package com.likelion.demo.domain.comment.entity;

import com.likelion.demo.domain.post.entity.Post;
import com.likelion.demo.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private String username;

    private String password;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id",nullable = false)
    private Post post;

    //댓글 수정(내용)
    public void modify(String content){
        this.content = content;
        this.updatedAt = LocalDateTime.now();
    }
}
