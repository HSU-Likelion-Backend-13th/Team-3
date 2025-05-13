package com.likelion.demo.domain.post.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ModifyPostReq {
    private String title;
    private String content;
    private String password;
}
