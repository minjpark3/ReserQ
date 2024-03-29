package com.example.mo_activity.domain.dto;


import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class CommentDto {

    private Long userId;
    private Long postsId;
    private String content;
}
