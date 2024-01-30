package com.sparta.reserq.domain.dto;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class PostsReqDto {
    private String title;
    private String content;
    private Long userId;
}