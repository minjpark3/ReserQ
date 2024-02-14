package com.example.mo_activity.domain.likes;

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
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "Likes",
                        columnNames = {"activityId","userId"}
                )
        }
)
public class PostsLikes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "activityId")
    private Long activityId;

    @Column(name = "status",nullable = false)
    private boolean status = true;

    @Column(name = "createDate")
    private LocalDateTime createDate;
    @PrePersist
    protected void onCreate() {
        createDate = LocalDateTime.now();
    }

    public PostsLikes(Long userId,Long activityId){
        this.userId = userId;
        this.activityId = activityId;
    }

}
