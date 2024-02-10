package com.example.mo_activity.domain.dto;


import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class CommentDto {
    @NotBlank
    private String content;
    @NotNull
    private Long postsId;
    @NotNull
    private Long userId;

}
