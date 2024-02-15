package com.example.mo_activity.client;

import lombok.*;

import java.time.LocalDateTime;

@Builder
public record NewsfeedClientReq(
        Long userId,
        Long relatedUserId,
        Long activityId,
        String activityType,
        LocalDateTime createDate
) {

}