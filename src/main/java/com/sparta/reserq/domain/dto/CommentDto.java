package com.sparta.reserq.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CommentDto {

    @NotBlank
    private String content;
    @NotNull
    private Long postsId;

}