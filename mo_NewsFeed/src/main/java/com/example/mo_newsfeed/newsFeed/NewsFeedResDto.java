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
    private Long relatedUserId;
    private Long activityId;
    private String activityType;

    public static NewsFeedResDto from(NewsFeed newsfeed) {
        return new NewsFeedResDto(
                newsfeed.getId(),
                newsfeed.getUserId(),
                newsfeed.getRelatedUserId(),
                newsfeed.getActivityType().name()
        );
    }
}
