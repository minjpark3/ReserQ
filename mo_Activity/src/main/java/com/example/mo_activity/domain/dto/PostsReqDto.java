package com.example.mo_activity.domain.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class PostsReqDto {
    private Long userId;
    private String title;
    private String content;

    public String content() {
        return content;
    }
}