package com.likelion.demo.domain.post.service;

import com.likelion.demo.domain.post.web.dto.*;

import java.util.List;

public interface PostService {
    // 게시글 작성
    CreatePostRes createOne(CreatePostReq createPostReq);

    PostDetailRes getById(Long postId);

    PostSummaryRes getAll();

    PostDetailRes modifyOne(Long postId, ModifyPostReq modifyPostReq);

    void deleteOne(Long postId, DeletePostReq deletePostReq);
}
