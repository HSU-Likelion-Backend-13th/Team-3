package com.likelion.demo.domain.comment.service;

import com.likelion.demo.domain.comment.entity.Comment;
import com.likelion.demo.domain.comment.exception.CommentNotFoundException;
import com.likelion.demo.domain.comment.repository.CommentRepository;
import com.likelion.demo.domain.comment.web.dto.*;
import com.likelion.demo.domain.post.entity.Post;
import com.likelion.demo.domain.post.exception.InvalidPasswordException;
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
        Comment comment = commentRepository.findById(commentId).orElseThrow(PostNotFoundException::new);

        if(!comment.getPost().getId().equals(postId)){
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
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(PostNotFoundException::new);

        if(!comment.getPost().getId().equals(postId)){
            throw new PostNotFoundException();
        }
        //비밀번호 검증
        if(!comment.getPassword().equals(modifyCommentReq.getPassword())){
            throw new InvalidPasswordException();
        }
        comment.modify(modifyCommentReq.getContent());

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
    public void deleteComment(Long postId, Long commentId, DeleteCommentReq deleteCommentReq) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(PostNotFoundException::new);

        if(!comment.getPost().getId().equals(postId)){
           throw new PostNotFoundException();
        }
       //비밀번호 검증
       if(!comment.getPassword().equals(deleteCommentReq.getPassword())){
            throw new InvalidPasswordException();
        }

        commentRepository.delete(comment);
    }
}
