package com.sparta.reserq.domain.dto;

import com.sparta.reserq.domain.posts.Posts;
import com.sparta.reserq.domain.user.User;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
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