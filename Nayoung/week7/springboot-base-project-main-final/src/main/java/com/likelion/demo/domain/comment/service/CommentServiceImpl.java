package com.likelion.demo.domain.comment.service;

import com.likelion.demo.domain.comment.entity.Comment;
import com.likelion.demo.domain.comment.exception.CommentInvalidPassword;
import com.likelion.demo.domain.comment.exception.CommentNotFoundException;
import com.likelion.demo.domain.comment.repository.CommentRepository;
import com.likelion.demo.domain.comment.web.dto.*;
import com.likelion.demo.domain.post.entity.Post;
import com.likelion.demo.domain.post.exception.PostNotFoundException;
import com.likelion.demo.domain.post.repository.PostRepository;
import com.likelion.demo.domain.post.web.dto.PostDetailRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Override
    public CreateCommentRes create(Long postId, CreateCommentReq createCommentReq) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);

        Comment comment = Comment.builder()
                .post(post)
                .content(createCommentReq.getContent())
                .username(createCommentReq.getUsername())
                .password(createCommentReq.getPassword())
                .build();

        Comment res = commentRepository.save(comment);
        return new CreateCommentRes(res.getId());
    }

    @Override
    public CommentSummeryRes getAllComment(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);

        return new CommentSummeryRes(
                commentRepository.findAllByPostId(postId).stream()
                        .map(c -> new CommentSummeryRes.CommentSummery(
                                c.getId(),
                                c.getContent(),
                                c.getUsername(),
                                c.getPassword(),
                                c.getCreatedAt()
                        ))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public CommentDetailRes getComment(Long postId, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);

        if(!comment.getPost().getId().equals(postId)) {
            throw new PostNotFoundException();
        }

        return new CommentDetailRes(
                comment.getId(),
                comment.getPost().getId(),
                comment.getContent(),
                comment.getUsername(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }

    // 댓글 수정
    @Transactional
    @Override
    public CommentDetailRes modifyOne(Long postId, Long commentId, ModifyCommentReq modifyCommentReq) {

        // 1. DB에서 postId로 Post 찾기
        Post foundPost = postRepository.findById(postId)
        // 404 - 게시글 없음
                .orElseThrow(PostNotFoundException::new);

        // 2. commentId로 Comment 찾기
        Comment foundComment = commentRepository.findById(commentId)
        // 404 - 댓글 없음
                .orElseThrow(CommentNotFoundException::new);

        // 3. 댓글이 해당 게시물에 속해 있는지 검증
        // 404 - 댓글 없음
        if (!foundComment.getPost().getId().equals(postId)) {
            throw new CommentNotFoundException();
        }

        // 4. 비밀번호 검증
        // 403 - 비밀번호 불일치
        if(!foundComment.getPassword().equals(modifyCommentReq.password())) {
            throw new CommentInvalidPassword();
        }

        // 5. comment 수정
        foundComment.modify(modifyCommentReq.content());

        // 6. CommentDetailRes 반환
        return new CommentDetailRes(
                foundComment.getId(),
                foundComment.getPost().getId(),
                foundComment.getContent(),
                foundComment.getUsername(),
                foundComment.getCreatedAt(),
                foundComment.getUpdatedAt()
        );
    }

    // 댓글 삭제
    @Transactional
    @Override
    public void deleteOne(Long postId, Long commentId, DeleteCommentReq deleteCommentReq) {
        // 1. 게시글 존재 확인
        Post foundPost = postRepository.findById(postId)
        // 404 - 게시글 존재하지 않음
                .orElseThrow(PostNotFoundException::new);

        // 2. 댓글 존재 확인
        Comment foundComment = commentRepository.findById(commentId)
        // 404 - 댓글 없음
                .orElseThrow(CommentNotFoundException::new);

        // 3. 댓글이 해당 게시물에 속해 있는지 검증
        // 404 - 댓글 없음
        if (!foundComment.getPost().getId().equals(postId)) {
            throw new CommentNotFoundException();
        }

        // 4. 비밀번호 검증
        // 403 - 비밀번호 불일치
        if(!foundComment.getPassword().equals(deleteCommentReq.password())) {
            throw new CommentInvalidPassword();
        }

        // 5. 삭제
        commentRepository.delete(foundComment);

    }
}

