package com.likelion.demo.domain.comment.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class ModifyCommentReq {
    private String content;
    private String password;
}
