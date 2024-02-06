package com.sparta.reserq.domain.newsFeed;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sparta.reserq.domain.comment.Comment;
import com.sparta.reserq.domain.posts.Posts;
import com.sparta.reserq.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class NewsFeed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnoreProperties({"posts"})
    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private User user;

    @JsonIgnoreProperties({"posts"})
    @JoinColumn(name = "postsId")
    @ManyToOne
    private Posts posts;

    @JsonIgnoreProperties({"posts"})
    @OneToMany(mappedBy = "newsFeed")
    private List<Comment> comments = new ArrayList<>();

    private LocalDateTime createDate;

    @PrePersist
    private void createDate(){
        this.createDate=LocalDateTime.now();
    }
    @CreationTimestamp
    private LocalDateTime updateData;


}
