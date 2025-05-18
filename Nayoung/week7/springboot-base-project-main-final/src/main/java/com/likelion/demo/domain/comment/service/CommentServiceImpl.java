package com.likelion.demo.domain.comment.service;

import com.likelion.demo.domain.comment.entity.Comment;
import com.likelion.demo.domain.comment.exception.CommentNotFoundException;
import com.likelion.demo.domain.comment.repository.CommentRepository;
import com.likelion.demo.domain.comment.web.dto.CommentDetailRes;
import com.likelion.demo.domain.comment.web.dto.CommentSummeryRes;
import com.likelion.demo.domain.comment.web.dto.CreateCommentReq;
import com.likelion.demo.domain.comment.web.dto.CreateCommentRes;
import com.likelion.demo.domain.post.entity.Post;
import com.likelion.demo.domain.post.exception.PostNotFoundException;
import com.likelion.demo.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
