package com.example.mo_activity.client;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NewsfeedClientReq{
        private Long userId;
        private String type;
        private Long activityId;

        }