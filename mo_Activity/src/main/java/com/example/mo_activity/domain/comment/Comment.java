package com.example.mo_activity.domain.comment;

import com.example.mo_activity.domain.posts.Posts;
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
public class Comment {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(length = 200, nullable = false)
        private String content;

        @Column(name = "userId")
        private Long userId;

        @JoinColumn(name = "postsId")
        @ManyToOne
        private Posts posts;


        private LocalDateTime createDate;

        @PrePersist
        private void createDate() {
                this.createDate = LocalDateTime.now();
        }

}