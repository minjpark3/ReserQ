package com.example.mo_newsfeed.newsFeed;

import lombok.*;



@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NewsFeedDto {
    private Long userId;
    private NewsFeed.ActivityType type;
    private Long activityId;
}