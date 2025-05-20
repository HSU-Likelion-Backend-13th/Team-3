package com.likelion.demo.domain.comment.service;

import com.likelion.demo.domain.comment.entity.Comment;
import com.likelion.demo.domain.comment.exception.CommentNotFoundException;
import com.likelion.demo.domain.comment.repository.CommentRepository;
import com.likelion.demo.domain.comment.web.dto.*;
import com.likelion.demo.domain.post.entity.Post;
import com.likelion.demo.domain.post.exception.InvalidPasswordException;
import com.likelion.demo.domain.post.exception.PostNotFoundException;
import com.likelion.demo.domain.post.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    // 댓글 생성
    @Override
    public CreateCommentRes createComment(Long postId, CreateCommentReq createCommentReq) {
        // 1. 받아온 postId로 DB에서 게시글 조회
        Post post = postRepository.findById(postId)
                // 만약 없다면 404 에러처리
                .orElseThrow(PostNotFoundException::new);

        // 2. dto 내부에 값들을 가져다가 빌더를 활용해서 댓글 생성
        Comment comment = Comment.builder()
                .post(post)
                .content(createCommentReq.getContent())
                .username(createCommentReq.getUsername())
                .password(createCommentReq.getPassword())
                .build();

        // 3. 생성한 댓글(comment)을 DB에 저장
        Comment savedComment = commentRepository.save(comment);

        // 4. 저장한 댓글id 반환
        return new CreateCommentRes(savedComment.getId());
    }

    // 댓글 전체 조회
    @Override
    public CommentSummaryRes getAllComment(Long postId) {
        // 1. 받아온 postId로 DB에서 게시글 조회
        Post post = postRepository.findById(postId)
                // 만약 없다면 404 에러처리
                .orElseThrow(PostNotFoundException::new);

        // 2. 댓글 리스트
        List<CommentSummaryRes.CommentSummary> commentSummaries = post.getCommentList().stream()
                .map(comment -> new CommentSummaryRes.CommentSummary(
                        comment.getId(),
                        comment.getContent(),
                        comment.getUsername(),
                        comment.getCreatedAt()
                ))
                .collect(Collectors.toList());

        return new CommentSummaryRes(commentSummaries);
    }

    // 댓글 단일 조회
    @Override
    public CommentDetailRes getComment(Long postId, Long commentId) {
        // 1.
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);

        if (!comment.getPost().getId().equals(postId)) {
           throw new PostNotFoundException();
        }

        return new CommentDetailRes(
                postId,
                comment.getId(),
                comment.getContent(),
                comment.getUsername(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }

    @Override
    public CommentDetailRes modifyComment(Long postId, Long commentId, ModifyCommentReq modifyCommentReq) {
        // 1. DB에서 인자로 받은 postId로 게시글 조회
        Comment modifyComment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);

        if(!modifyComment.getPost().getId().equals(postId)) {
            throw new PostNotFoundException();
        }

        // 비밀번호 검증
        if(!modifyComment.getPassword().equals(modifyCommentReq.getPassword())) {
            throw new CommentNotFoundException();
        }

        modifyComment.modify(modifyCommentReq.getContent());

        return new CommentDetailRes(
                modifyComment.getId(),
                modifyComment.getPost().getId(),
                modifyComment.getUsername(),
                modifyComment.getContent(),
                modifyComment.getCreatedAt(),
                modifyComment.getUpdatedAt()
        );
    }

    @Override
    public void deleteComment(Long postId, Long commentId, DeleteCommentReq deleteCommentReq) {
        // DB에서 댓글 조회
        Comment deleteComment = commentRepository.findById(commentId)
                // 없으면 댓글 404 에러 처리
                .orElseThrow(CommentNotFoundException::new);

        // 삭제할 댓글의 postId와 인자로 받은 postId가 같지 않을 경우
        if(!deleteComment.getPost().getId().equals(postId)) {
            // 404
            throw new PostNotFoundException();
        }

        // 삭제할 댓글의 비밀번호와 인자로 받은 비밀번호가 일치하지 않을 경우
        if(!deleteComment.getPassword().equals(deleteCommentReq.getPassword())) {
            // 403
            throw new CommentNotFoundException();
        }

        commentRepository.delete(deleteComment);
    }
}
