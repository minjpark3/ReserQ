package com.sparta.reserq.dto;

import com.sparta.reserq.domain.post.Post;
import com.sparta.reserq.domain.user.User;
import lombok.Data;

import java.awt.*;

@Data
public class PostUploadDto {
    private String title;
    private String content;

    public Post toEntity(String title, String content, User user){
        return Post.builder()
                .title(title)
                .content(content)
                .user(user)
                .build();
    }

}

