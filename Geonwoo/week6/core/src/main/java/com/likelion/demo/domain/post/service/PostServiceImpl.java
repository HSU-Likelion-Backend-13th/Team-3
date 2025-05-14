package com.likelion.demo.domain.post.service;

import com.likelion.demo.domain.post.entity.Post;
import com.likelion.demo.domain.post.entity.PostState;
import com.likelion.demo.domain.post.exception.InvalidPasswordException;
import com.likelion.demo.domain.post.exception.PostNotFoundException;
import com.likelion.demo.domain.post.repository.PostRepository;
import com.likelion.demo.domain.post.web.dto.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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
    public PostDetailRes getById(@PathVariable Long postId) {
        // 1. postId에 해당하는 Post - DB에서 조회
        Post post = postRepository.findById(postId)
                // 404- postId에
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
        // 1. DB에서 모든 게시글을 조회(레포지토리 사용)
        List<Post> posts = postRepository.findAll();
        // 2. 모든 게시글을 담은 List 형식으로 반환  post -> PostSummaryRes로 변환
        List<PostSummaryRes.PostSummary> postSummaryList = new ArrayList<>();
        for (Post post : posts){
            PostSummaryRes.PostSummary postSummary = new PostSummaryRes.PostSummary(
                    post.getId(),
                    post.getTitle(),
                    post.getUsername(),
                    post.getCreatedAt()
            );
            postSummaryList.add(postSummary);
        }
        // 반환
        return new PostSummaryRes(postSummaryList);
    }
    @Transactional
    @Override
    public PostDetailRes modifyOne(Long postId, ModifyPostReq modifyPostReq) {
        // 1. DB에서 수정할 Post를 찾아온다
        Post foundPost = postRepository.findById(postId)
                // 404 - 게시글 없음
                .orElseThrow(PostNotFoundException::new);
        // 2. 비밀번호 검증
        if (!foundPost.getPassword().equals(modifyPostReq.getPassword())) {
            // 403 - 비밀번호 불일치
            throw new InvalidPasswordException();
        }
        // 3. Post modifying
        foundPost.modify(modifyPostReq.getTitle(), modifyPostReq.getContent());
        // return
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
    @Transactional
    @Override
    public void deleteOne(Long postId, DeletePostReq deletePostReq) {
        // 1. Db에서 Post 조회
        Post post = postRepository.findById(postId)
                // 404 - 게시글이 존재하지 않습니다.
                .orElseThrow(PostNotFoundException::new);

        // 2. 비밀번호 검증
        if (!post.getPassword().equals(deletePostReq.getPassword())) {
            // 403 - 비밀번호가 일치하지 않습니다.
            throw new InvalidPasswordException();
        }
        // 3. Delete (똑같이 Entity에서 처리)
        postRepository.delete(post);
    }

}
