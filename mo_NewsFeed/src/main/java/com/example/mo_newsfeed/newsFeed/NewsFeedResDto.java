package com.example.mo_newsfeed.newsFeed;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class NewsFeedResDto {
    private Long userId;
    private String activityType;
    private Long activityId;
}
