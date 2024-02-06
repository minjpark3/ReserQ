package com.sparta.reserq.domain.likes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sparta.reserq.domain.comment.Comment;
import com.sparta.reserq.domain.posts.Posts;
import com.sparta.reserq.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "likes_uk",
                        columnNames = {"commentId","userId"}
                )
        }
)           //N
public class CommentLikes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "commentId")
    private Comment comment;


    @JsonIgnoreProperties({"posts"})
    @JoinColumn(name = "userId")
    @ManyToOne
    private User user;

    private LocalDateTime createDate;

    @PrePersist //디비에 insert되기 전에 실행
    private void createDate(){
        this.createDate=LocalDateTime.now();
    }

}
