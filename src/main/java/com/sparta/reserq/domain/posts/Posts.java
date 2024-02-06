package com.sparta.reserq.domain.posts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sparta.reserq.domain.comment.Comment;
import com.sparta.reserq.domain.likes.PostsLikes;
import com.sparta.reserq.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content; // ex)나 오늘 너무 피곤하다~

    // @JsonIgnoreProperties({"posts"})
    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private User user; //한명의 유저는 N개의 포스트가능 1개의 포스트는 1명의 유저

    @JsonIgnoreProperties({"posts"})
    @OneToMany(mappedBy = "posts")
    private List<PostsLikes> likes;
    @Transient //DB에 컬럼이 만들어지지 않는다.
    private boolean likeState;
    @Transient
    private int likeCount;

    //@OrderBy("id DESC")
    @JsonIgnoreProperties({"posts"})
    @OneToMany(mappedBy = "posts")
    private List<Comment>comments;
    private LocalDateTime createDate;

    @PrePersist //디비에 insert되기 전에 실행
    private void createDate(){
        this.createDate=LocalDateTime.now();
    }
    @CreationTimestamp
    private LocalDateTime updateData;


}
