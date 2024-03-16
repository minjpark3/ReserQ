package com.sparta.reserq.domain.dto;

import com.sparta.reserq.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsReqDto {
    private String title;
    private String content;

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .build();
    }
}