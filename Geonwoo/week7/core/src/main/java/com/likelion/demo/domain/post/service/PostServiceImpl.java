package com.likelion.demo.domain.post.service;

import com.likelion.demo.domain.post.entity.Post;
import com.likelion.demo.domain.post.entity.PostState;
import com.likelion.demo.domain.post.exception.InvalidPasswordException;
import com.likelion.demo.domain.post.exception.PostNotFoundException;
import com.likelion.demo.domain.post.repository.PostRepository;
import com.likelion.demo.domain.post.web.dto.*;
import com.likelion.demo.domain.post.web.dto.PostSummaryRes.PostSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // 게시글 단건 조회
    @Override
    public PostDetailRes getById(Long postId) {

        // 1. postId가지고 Post 엔티티 찾기
        // 404: 게시글 없음
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        // 2. Post를 가지고 PostDetailRes 반환
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

    // 게시글 전체 조회
    @Override
    public PostSummaryRes getAll() {
        // 1. DB 에서 모든 Post 조회
        List<Post> posts = postRepository.findAll();

        // 게시글 리스트
        List<PostSummary> postSummaryList= new ArrayList<>();

        for (Post post : posts) {
            PostSummary postSummary = new PostSummary(
                    post.getId(),
                    post.getTitle(),
                    post.getUsername(),
                    post.getCreatedAt()
            );
            postSummaryList.add(postSummary);
        }
        // 2. PostSummaryRes 반환
        return new PostSummaryRes(postSummaryList);
    }

    // 게시글 수정
    @Transactional
    @Override
    public PostDetailRes modifyOne(Long postId, ModifyPostReq modifyPostReq) {
        // 1. DB 에서 postId로 Post 찾기
        Post foundPost = postRepository.findById(postId)
                // 404 - 게시글 없음
                .orElseThrow(PostNotFoundException::new);

        // 2. 비밀번호 검증
        // 403 - 비밀번호 불일치
        if (!foundPost.getPassword().equals(modifyPostReq.getPassword())) {
            throw new InvalidPasswordException();
        }

        // 3. post 수정
        foundPost.modify(modifyPostReq.getTitle(), modifyPostReq.getContent());

        // PostDetailRes 반환
        return new PostDetailRes(
                foundPost.getId(),
                foundPost.getTitle(),
                foundPost.getContent(),
                foundPost.getUsername(),
                foundPost.getPassword(),
                foundPost.getState(),
                foundPost.getCreatedAt(),
                foundPost.getUpdatedAt()
        );
    }

    // 게시글 삭제
    @Transactional
    @Override
    public void deleteOne(Long postId, DeletePostReq deletePostReq) {
        // 1. 게시글 존재 확인
        // 404 - 게시글 존재하지 않음
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        // 2. 비밀번호 검증
        if (!post.getPassword().equals(deletePostReq.getPassword())) {
            // 403 - 비밀번호 불일치
            throw new InvalidPasswordException();
        }

        // 3. 삭제
        postRepository.delete(post);
    }

}
