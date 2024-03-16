package com.example.mo_newsfeed.newsFeed;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "Newsfeed")
public class NewsFeed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "actionUserId")
    private Long userId;

    @Column(name = "related_user_id")
    private Long relatedUserId;

    @Column(name = "activityId")
    private Long activityId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ActivityType activityType;


    @Column(name = "createDate")
    private LocalDateTime createDate;
    @PrePersist
    protected void onCreate() {
        createDate = LocalDateTime.now();
    }


}
