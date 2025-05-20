package com.likelion.demo.domain.comment.web.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ModifyCommentReq {
    private String password;
    private String content;
}
