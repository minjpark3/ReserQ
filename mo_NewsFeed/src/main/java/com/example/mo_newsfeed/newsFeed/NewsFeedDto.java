package com.example.mo_newsfeed.newsFeed;

import lombok.*;



@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NewsFeedDto {
    private Long userId;
    private Long relatedUserId;
    private Long activityId;
    private ActivityType activityType;
}