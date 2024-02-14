package com.example.mo_newsfeed.newsFeed;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.Arrays;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class NewsFeed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userId", nullable = false)
    private Long userId;

    @Column(name = "activityId")
    private Long activityId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ActivityType type;


    @Column(name = "createDate")
    private LocalDateTime createDate;
    @PrePersist
    protected void onCreate() {
        createDate = LocalDateTime.now();
    }


    public NewsFeed(Long userId, ActivityType type, Long activityId) {
        this.userId = userId;
        this.type = type;
        this.activityId = activityId;
    }

    public enum ActivityType {
        POSTS,
        COMMENT,
        FOLLOWER,
        POSTSLIKES

    }

}
