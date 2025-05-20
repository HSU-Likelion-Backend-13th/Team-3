package com.likelion.demo.domain.post.entity;

import com.likelion.demo.domain.comment.entity.Comment;
import com.likelion.demo.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Post extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String username;
    private String password;
    // 작성일시, 수정일시

    @Enumerated(EnumType.STRING)
    private PostState state;

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList = new ArrayList<>();

    // 게시글 수정(제목, 내용)
    public void modify(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
