package com.example.mo_activity.domain;

import lombok.Getter;

@Getter
public enum ActivityType {
    POSTS,
    COMMENT,
    FOLLOWER,
    POSTSLIKES;

    public static ActivityType fromString(String text) {
        for (ActivityType activityType : ActivityType.values()) {
            if (activityType.name().equalsIgnoreCase(text)) {
                return activityType;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found in ActivityType enum");
    }
}
