package com.likelion.demo.domain.post.service;

import com.likelion.demo.domain.post.web.dto.*;

public interface PostService {
    // 게시글 작성
    CreatePostRes createOne(CreatePostReq createPostReq);

    //게시글 단건 조회
    PostDetailRes getById(Long postId);

    //게시글 전체 조회
    PostSummaryRes getAll();

    //게시글 수정
    PostDetailRes modifyOne(Long postId, ModifyPostReq modifyPostReq);
}
