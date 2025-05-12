package com.likelion.demo.domain.post.entity;

import com.likelion.demo.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor // 매개변수 없는 생성자 자동 생성, JPA는 엔티티 클래스에 기본 생성자가 필수이므로 반드시 필요
@Builder // 객체를 빌더 패턴으로 생성할 수 있게 해줌
@AllArgsConstructor // 모든 필드를 매개변수로 받는 생성자를 자동 생성
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String username;
    private String password;
    // 작성일시, 수정일시

    @Enumerated(EnumType.STRING)
    private PostState state;

}

/* @Builder 어노테이션은 이렇게 객체를 빌더 패턴으로 생성할 수 있게 해준다.
* Post post = Post.builder()
    .title("제목")
    .content("내용")
    .username("홍길동")
    .password("1234")
    .state(PostState.PROGRESS)
    .build();

* */