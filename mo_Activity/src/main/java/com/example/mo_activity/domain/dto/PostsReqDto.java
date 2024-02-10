package com.example.mo_activity.domain.dto;

import com.example.mo_activity.domain.posts.Posts;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class PostsReqDto {
    private Long userId;
    private String title;
    private String content;


}