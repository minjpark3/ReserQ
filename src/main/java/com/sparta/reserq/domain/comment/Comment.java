package com.sparta.reserq.domain.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sparta.reserq.domain.likes.CommentLikes;
import com.sparta.reserq.domain.newsFeed.NewsFeed;
import com.sparta.reserq.domain.posts.Posts;
import com.sparta.reserq.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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

        @JoinColumn(name = "userId")
        @ManyToOne
        private User user;

        @JoinColumn(name = "postsId")
        @ManyToOne
        private Posts posts;

//        @JsonIgnoreProperties({"comment"})
//        @OneToMany(mappedBy = "comment")
//        private List<CommentLikes> likes;

        @ManyToOne
        @JoinColumn(name = "newsFeedId")
        private NewsFeed newsFeed;

        private LocalDateTime createDate;

        @PrePersist
        private void createDate() {
            this.createDate = LocalDateTime.now();
        }

//        @Transient
//        private boolean likeState;
//
//        @Transient
//        private int likeCount;

}

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(length = 100, nullable = false)
//    private String Content;
//
//    @JsonIgnoreProperties({"posts"})
//    @JoinColumn(name="userId")
//    @ManyToOne
//    @JsonIgnore
//    private User user;
//
//    @JoinColumn(name="postsId")
//    @ManyToOne
//    @JsonIgnore
//    private Posts posts;
//
//    @JsonIgnoreProperties({"posts"})
//    @OneToMany(mappedBy = "posts")
//    private List<CommentLikes> likes;
//    @Transient //DB에 컬럼이 만들어지지 않는다.
//    private boolean likeState;
//    @Transient
//    private int likeCount;
//
//    private LocalDateTime createDate;
//
//    @PrePersist //디비에 insert되기 전에 실행
//    private void createDate(){
//        this.createDate=LocalDateTime.now();
//    }
//
//
//}
