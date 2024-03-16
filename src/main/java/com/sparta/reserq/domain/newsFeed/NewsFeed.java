package com.sparta.reserq.domain.newsFeed;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sparta.reserq.domain.comment.Comment;
import com.sparta.reserq.domain.posts.Posts;
import com.sparta.reserq.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class NewsFeed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @JoinColumn(name = "postsId")
    @ManyToOne
    private Posts posts;


    private LocalDateTime createDate;

    @PrePersist
    private void createDate(){
        this.createDate=LocalDateTime.now();
    }
    @CreationTimestamp
    private LocalDateTime updateData;


}
