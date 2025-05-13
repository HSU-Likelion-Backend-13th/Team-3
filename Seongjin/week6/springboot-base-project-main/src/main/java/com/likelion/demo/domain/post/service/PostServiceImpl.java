package com.likelion.demo.domain.post.service;

import com.likelion.demo.domain.post.entity.Post;
import com.likelion.demo.domain.post.entity.PostState;
import com.likelion.demo.domain.post.exception.PostNotFoundException;
import com.likelion.demo.domain.post.repository.PostRepository;
import com.likelion.demo.domain.post.web.dto.*;
import com.likelion.demo.domain.post.web.dto.PostSummaryRes.PostSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    // 게시글 작성
    @Override
    public CreatePostRes createOne(CreatePostReq createPostReq) {
        // 1. createPostReq가지고 Post Entity 생성
        Post post = Post.builder()
                .title(createPostReq.getTitle())
                .content(createPostReq.getContent())
                .username((createPostReq.getUsername()))
                .password(createPostReq.getPassword())
                .state(PostState.PROGRESS)
                .build();

        // 2. repository Post 저장 (postRepository 사용)
        Post savedPost = postRepository.save(post);

        // 3. 반환 CreatePostRes
        return new CreatePostRes(savedPost.getId());
    }

    //게시글 단건 조회
    @Override
    public PostDetailRes getById(Long postId) {
        // 1. postId에 해당하는 Post - DB에서 조회
        Post post = postRepository.findById(postId)
                //404 - postId에
                .orElseThrow(PostNotFoundException::new);

        // 2. PostDetailRes 반환
        return new PostDetailRes(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getUsername(),
                post.getPassword(),
                post.getState(),
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }

    @Override
    public PostSummaryRes getAll() {
        // 1. DB에서 모든 Post 조회 (postRepository)
        List<Post> posts = postRepository.findAll();

        // 2. posts -> PostSummaryRes 변환
        List<PostSummary> postSummaryList = new ArrayList<>();
        for(Post post : posts) {
            PostSummary postSummary = new PostSummary(
              post.getId(),
              post.getTitle(),
              post.getUsername(),
              post.getCreatedAt()
            );
            postSummaryList.add(postSummary);
        }

        // 3. 반환
        return new PostSummaryRes(postSummaryList);
    }
}
